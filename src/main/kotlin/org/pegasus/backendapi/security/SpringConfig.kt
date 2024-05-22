package org.pegasus.backendapi.security

import org.pegasus.backendapi.service.UserService
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.filter.OncePerRequestFilter

val securityInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {
    bean { BCryptPasswordEncoder() }
    bean { JwtUtil() }

    bean<AuthenticationManager> {
        val authenticationConfiguration = ref<AuthenticationConfiguration>()
        authenticationConfiguration.authenticationManager
    }

    bean<OncePerRequestFilter> {
        val jwtUtil: JwtUtil = ref()
        val userService: UserService = ref()

        JwtRequestFilter(jwtUtil, userService)
    }

    bean {
        val userService: UserService = ref()
        val jwtRequestFilter: JwtRequestFilter = ref()

        SecurityConfig(userService, jwtRequestFilter)
    }
}
