package org.pegasus.backendapi.user

import jakarta.persistence.EntityManager
import org.pegasus.backendapi.user.service.UserInternalService
import org.pegasus.backendapi.user.service.UserService
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.router
import java.util.*

val userInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {

    bean {
        val entityManager = ref<EntityManager>()
        UserRepository(entityManager)
    }

    bean {
        val userRepository = ref<UserRepository>()
        UserInternalService(userRepository)
    }

    bean {
        val userInternalService = ref<UserInternalService>()
        val userRepository = ref<UserRepository>()
        UserService(userInternalService, userRepository)
    }

    bean {
        val userService = ref<UserService>()
        UserHandler(userService)
    }

    bean {
        val userHandler = ref<UserHandler>()

        router {
            "/api/v1/user".nest {
                GET("/{id}") { request ->
                    val id = request.pathVariable("id").map { UUID.fromString(it) }.orElse(null)
                    val response = userHandler.user(id)

                    if (response == null) ServerResponse.noContent().build() else ServerResponse.ok().body(response)
                }
                GET("/list") { _ ->
                    val response = userHandler.all()
                    if (response.isEmpty()) ServerResponse.noContent().build() else ServerResponse.ok().body(response)
                }
            }
        }
    }

}
