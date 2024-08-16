package org.pegasus.backendapi.auth

import org.pegasus.backendapi.auth.AuthController.LoginRequest
import org.pegasus.backendapi.security.JwtService
import org.pegasus.backendapi.user.service.UserService
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.router

val authInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {

    bean {
        val userService = ref<UserService>()
        val jwtService = ref<JwtService>()

        AuthController(userService, jwtService)
    }

    bean {
        val authController = ref<AuthController>()

        router {
            "/api/v1/auth".nest {
                POST("/") { request ->
                    val body = request.body(LoginRequest::class.java)
                    val response = authController.login(body)
                    if (response == null) {
                        ServerResponse.badRequest().build()
                    } else {
                        ServerResponse.ok().body(response)
                    }
                }
                POST("/refresh") {
                    ServerResponse.ok().body(authController.refresh())
                }
            }
        }

    }
}
