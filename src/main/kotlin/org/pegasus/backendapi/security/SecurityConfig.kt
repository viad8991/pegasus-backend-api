package org.pegasus.backendapi.security

import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.user.UserService
import org.springframework.context.annotation.Bean
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
class SecurityConfig(
    private val userService: UserService,
    private val jwtRequestFilter: JwtRequestFilter
) {

    private val log = logger()

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain =
        http
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
                exceptionHandling.authenticationEntryPoint { request, response, authException ->
                    log.info { authException }
//                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
                }
            }
            // .formLogin(Customizer.withDefaults())
            // .oauth2Login(Customizer.withDefaults())
            // .httpBasic(Customizer.withDefaults())
            .cors(Customizer.withDefaults())
            .csrf { it.disable() }
            .build()

}
