package org.pegasus.backendapi.security

import org.apache.logging.log4j.kotlin.logger
import org.springframework.context.annotation.Bean
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
class SecurityConfig(private val jwtRequestFilter: JwtRequestFilter) {

    private val log = logger()

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .authorizeHttpRequests { requests ->
                requests
                    .requestMatchers("/").anonymous()
                    .requestMatchers("/error").anonymous()
                    .requestMatchers("/login").anonymous()
                    .requestMatchers("/register").anonymous()

                    .requestMatchers("/api/v1/auth/register").anonymous()
                    .requestMatchers("/api/v1/auth/login").anonymous()

                    .requestMatchers("/api/v1/auth/route").permitAll()
                    .requestMatchers("/api/v1/auth/hotel").permitAll()
                    .requestMatchers("/api/v1/auth/entertainment").permitAll()

                    .requestMatchers("/api/v1/auth/comment").permitAll()
                    .requestMatchers("/api/v1/auth/share").permitAll()

                    .requestMatchers("/favicon.ico").permitAll() // это явно выглядит как костыль

                    .requestMatchers("/**").authenticated()

                    .anyRequest().authenticated()
            }
            // .httpBasic(Customizer.withDefaults())
            // .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
            // .userDetailsService { username -> userService.findByUsername(username) }
            // .formLogin { form -> form.loginPage("/login").permitAll() }
            .logout { logout -> logout.permitAll() }
            .exceptionHandling { exceptionHandling ->
                exceptionHandling.authenticationEntryPoint { request, _, ex ->
                    log.debug("request without auth to url: ${request.requestURL}\nException: ${ex.message}", ex)
                    LoginUrlAuthenticationEntryPoint("/login")
                }
            }
            .cors(Customizer.withDefaults())
            .csrf { it.disable() }
            .build()
}
