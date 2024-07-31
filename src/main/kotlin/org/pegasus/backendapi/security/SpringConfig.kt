package org.pegasus.backendapi.security

import org.pegasus.backendapi.user.UserService
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.filter.OncePerRequestFilter

val securityInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {

    bean<JwtService>()

    bean<OncePerRequestFilter> { JwtRequestFilter(ref(), ref()) }

    bean<SecurityFilterChain> {
        val jwtRequestFilter = ref<JwtRequestFilter>()
        val userService = ref<UserService>()
        val httpSecurity = ref<HttpSecurity>()

        httpSecurity
            .authorizeHttpRequests { requests ->
                requests
                    .requestMatchers("/api/v1/user/login").anonymous()
                    .requestMatchers("/api/v1/user").authenticated()
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
