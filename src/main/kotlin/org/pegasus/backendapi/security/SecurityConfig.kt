package org.pegasus.backendapi.security

import org.apache.logging.log4j.kotlin.logger
import org.springframework.context.annotation.Bean
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
class SecurityConfig(private val jwtRequestFilter: JwtRequestFilter) {

    private val log = logger()

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .authorizeHttpRequests { requests ->
                requests
                    .requestMatchers("/api/v1/auth/register").permitAll()
                    .requestMatchers("/api/v1/auth/login").permitAll()

                    .requestMatchers("/api/v1/auth/route").permitAll()
                    .requestMatchers("/api/v1/auth/hotel").permitAll()
                    .requestMatchers("/api/v1/auth/entertainment").permitAll()

                    .requestMatchers("/api/v1/auth/comment").permitAll()
                    .requestMatchers("/api/v1/auth/share").permitAll()

                    .requestMatchers("/**").permitAll()
                    .anyRequest().authenticated()
            }
            // .httpBasic(Customizer.withDefaults())
            // .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            // .formLogin { form -> form.loginPage("/login").permitAll() }
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
            // .userDetailsService { username -> userService.findByUsername(username) }
            // .exceptionHandling { it.authenticationEntryPoint { request, responce, ex -> responce.sendError(responce.status, "un auth") } }
            .logout { logout -> logout.permitAll() }
            .cors(Customizer.withDefaults())
            .csrf { it.disable() }
            .build()
}
