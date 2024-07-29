package org.pegasus.backendapi.security

import org.pegasus.backendapi.user.UserService
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.web.filter.OncePerRequestFilter

val securityInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {

    bean<JwtService>()

    bean<OncePerRequestFilter> { JwtRequestFilter(ref(), ref()) }

    bean {
        val userService = ref<UserService>()
        val jwtRequestFilter = ref<JwtRequestFilter>()
        SecurityConfig(userService, jwtRequestFilter)
    }

}
