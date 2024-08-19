package org.pegasus.backendapi.security

import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.user.service.UserInternalService
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.messaging.rsocket.RSocketStrategies
import org.springframework.messaging.rsocket.annotation.support.RSocketMessageHandler
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.rsocket.RSocketSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.rsocket.core.PayloadSocketAcceptorInterceptor
import org.springframework.security.rsocket.metadata.SimpleAuthenticationEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.bind.annotation.RequestMethod.DELETE
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RequestMethod.POST
import org.springframework.web.bind.annotation.RequestMethod.PUT
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

private val log = logger("securityInitializer")

val securityInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {

    /* JWT settings */
    bean<JwtService>()

    bean<JwtFilterManager> {
        JwtFilterManager(ref(), ref())
    }

    /* HTTP request request settings */
    bean<WebMvcConfigurer> {
        object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                val requestMethodNames = listOf(GET.name, POST.name, PUT.name, DELETE.name).toTypedArray()

                registry
                    .addMapping("/**")
                    .allowedOrigins("http://localhost:3000")
                    .allowedMethods(*requestMethodNames)
            }
        }
    }

    bean<SecurityFilterChain> {
        val jwtFilterManager = ref<JwtFilterManager>()
        val userService = ref<UserInternalService>()
        val httpSecurity = ref<HttpSecurity>()

        httpSecurity
            .authorizeHttpRequests { requests ->
                requests
                    .requestMatchers("/api/v1/auth").permitAll()

                    // TODO for test
                    .requestMatchers("/api/v1/category/**").permitAll()
                    .requestMatchers("/api/v1/family/**").permitAll()

                    .requestMatchers("/api/v1/user/**").authenticated()
                    .requestMatchers("/api/v1/transaction").authenticated()

                    .requestMatchers("/**").permitAll()
            }
            .userDetailsService(userService)
            // отключаем сессии
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            // авторизация
            .addFilterBefore(jwtFilterManager, UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling { exceptionHandling ->
                exceptionHandling.authenticationEntryPoint { _, _, authException ->
                    log.info { authException } // response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
                }
            }
            // .formLogin(Customizer.withDefaults())
            // .oauth2Login(Customizer.withDefaults())
            // .httpBasic(Customizer.withDefaults())
            .cors(Customizer.withDefaults())
            .csrf { it.disable() }
            .build()
    }

    /* SOCKET connection settings */
    bean<RSocketMessageHandler> {
        val rSocketStrategies = RSocketStrategies.builder()
        rSocketStrategies.encoders {
            // it.add(BearerTokenAuthenticationEncoder())
            it.add(SimpleAuthenticationEncoder())
            it.add(Jackson2JsonEncoder())
        }

        rSocketStrategies.decoders {
            it.add(Jackson2JsonDecoder())
        }

        RSocketMessageHandler().also { handler ->
            handler.rSocketStrategies = rSocketStrategies.build()
        }
    }

    // TODO
    //  bean<NimbusReactiveJwtDecoder> {
    //      val jwtService = ref<JwtService>()
    //      NimbusReactiveJwtDecoder.withSecretKey(jwtService.secretKey).build()
    //  }

    bean<PayloadSocketAcceptorInterceptor> {
        val jwtFilterManager = ref<JwtFilterManager>()

        val rSocketSecurity = ref<RSocketSecurity>()
        rSocketSecurity
            .authorizePayload { authorize ->
                authorize.setup()
                authorize.route("api.v1.notification").authenticated()
                authorize.anyRequest().authenticated()
                authorize.anyExchange().authenticated()
            }
            // .jwt { jwtSpec -> jwtSpec.authenticationManager(jwtFilterManager) }
            .authenticationManager(jwtFilterManager)
            .simpleAuthentication(Customizer.withDefaults())
            .build()
    }

}
