package org.pegasus.backendapi.restservice

import org.pegasus.backendapi.restservice.controller.GreetingController
import org.pegasus.backendapi.restservice.controller.impl.GreetingControllerImpl
import org.pegasus.backendapi.restservice.service.GreetingService
import org.pegasus.backendapi.restservice.service.impl.GreetingServiceImpl
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.web.reactive.function.server.coRouter

val greetingInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {
    bean<GreetingController> { GreetingControllerImpl(ref()) }

    bean {
        val errorHandler = ref<ErrorHandler>()
        val greetingService = ref<GreetingService>()
        coRouter {
            "/api".nest {
                "/v1".nest {
                    GET("list", greetingService::createResponse)
                }
            }
            onError<RuntimeException> { throwable, serverRequest ->
                errorHandler.handleError(throwable, serverRequest)
            }
        }
    }

    bean<GreetingService> { GreetingServiceImpl() }
}
