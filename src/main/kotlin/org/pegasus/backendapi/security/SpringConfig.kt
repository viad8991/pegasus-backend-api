package org.pegasus.backendapi.security

import org.pegasus.backendapi.user.service.UserInternalService
import org.pegasus.backendapi.user.service.UserService
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.bind.annotation.RequestMethod.*
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

val securityInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {

    bean<JwtService>()

    bean<OncePerRequestFilter> { JwtRequestFilter(ref(), ref()) }

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
        val jwtRequestFilter = ref<JwtRequestFilter>()
        val userService = ref<UserInternalService>()
        val httpSecurity = ref<HttpSecurity>()

        httpSecurity
            .authorizeHttpRequests { requests ->
                requests
                    .requestMatchers("/api/v1/auth").anonymous()

                    .requestMatchers("/api/v1/user/**").authenticated()
                    .requestMatchers("/api/v1/transaction").authenticated()

                    .requestMatchers("/**").permitAll()
            }
            .userDetailsService(userService)
            // отключаем сессии
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            // авторизация
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling { exceptionHandling ->
                exceptionHandling.authenticationEntryPoint { _, _, authException ->
                    println(authException) // response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
                }
            }
            // .formLogin(Customizer.withDefaults())
            // .oauth2Login(Customizer.withDefaults())
            // .httpBasic(Customizer.withDefaults())
            .cors(Customizer.withDefaults())
            .csrf { it.disable() }
            .build()
    }

}
