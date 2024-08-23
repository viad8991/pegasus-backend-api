package org.pegasus.backendapi.chat

import jakarta.persistence.EntityManager
import org.pegasus.backendapi.chat.model.MessageRequest
import org.pegasus.backendapi.chat.service.MessageInternalService
import org.pegasus.backendapi.chat.service.MessageService
import org.pegasus.backendapi.family.service.FamilyInternalService
import org.pegasus.backendapi.user.service.UserInternalService
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.router

val chatInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {

    bean {
        val entityManager = ref<EntityManager>()
        MessageRepository(entityManager)
    }

    bean {
        MessageInternalService()
    }

    bean {
        val userService = ref<UserInternalService>()
        val familyService = ref<FamilyInternalService>()
        val messageRepository = ref<MessageRepository>()

        MessageService(userService, familyService, messageRepository)
    }

    bean {
        val messageService = ref<MessageService>()
        MessageHandler(messageService)
    }

    bean {
        val messageHandler = ref<MessageHandler>()

        router {
            "/api/v1/messages".nest {
                // TODO вообще не уверен, что нужен этот роут, т.к. лучше это делать посредством
                //  RequestChannel по rSocket
                POST("/") { serverRequest ->
                    val message = serverRequest.body(MessageRequest::class.java)
                    val response = messageHandler.message(message)

                    ServerResponse.ok().body(response)
                }
                GET("/chat") { _ ->
                    val response = messageHandler.messages()

                    if (response.isEmpty()) {
                        ServerResponse.noContent().build()
                    } else {
                        ServerResponse.ok().body(response)
                    }
                }
            }

        }
    }

}
