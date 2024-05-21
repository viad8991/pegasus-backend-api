package org.pegasus.backendapi.auth

import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans

val authInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {
    bean {
        // val userService: UserService = ref()
        // val jwtUtil: JwtUtil = ref()
        // val authenticationManager: AuthenticationManager = ref()

        // AuthController(userService, jwtUtil, authenticationManager)
    }
}
