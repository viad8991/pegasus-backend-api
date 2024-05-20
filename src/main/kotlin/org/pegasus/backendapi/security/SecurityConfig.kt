package org.pegasus.backendapi.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig(private val userService: UserService) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { requests ->
                requests
                    .requestMatchers("/register", "/login").permitAll()
                    .requestMatchers("/", "/home").authenticated()

                    .anyRequest().authenticated()
            }
            .formLogin { form ->
                form
                    .loginPage("/login").permitAll()
            }
            .userDetailsService { username -> userService.findByUsername(username) }
//            .authenticationManager { authManager ->  authManager.}
            .logout { logout -> logout.permitAll() }

        return http.build()
    }
}