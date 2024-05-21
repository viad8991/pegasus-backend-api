package org.pegasus.backendapi.security

import io.github.oshai.kotlinlogging.KotlinLogging.logger
import org.springframework.context.annotation.Bean
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
class SecurityConfig(
    private val userService: UserService,
    private val jwtRequestFilter: JwtRequestFilter,
) {
    private val log = logger(this.javaClass.name)

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .authorizeHttpRequests { requests ->
                requests
                    .requestMatchers("/api/v1/auth/register").permitAll()
                    .requestMatchers("/api/v1/auth/login").permitAll()
                    .requestMatchers("/**").permitAll()
                    .anyRequest().authenticated()
            }
//            .httpBasic(Customizer.withDefaults())
//            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
//            .formLogin { form -> form.loginPage("/login").permitAll() }
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
//            .userDetailsService { username -> userService.findByUsername(username) }
//            .exceptionHandling { it.authenticationEntryPoint { request, responce, ex -> responce.sendError(responce.status, "un auth") } }
            .logout { logout -> logout.permitAll() }
            .cors(Customizer.withDefaults())
            .csrf { it.disable() }
            .build()
}
