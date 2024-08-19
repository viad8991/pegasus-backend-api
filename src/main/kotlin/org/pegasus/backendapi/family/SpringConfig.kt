package org.pegasus.backendapi.family

import jakarta.persistence.EntityManager
import org.pegasus.backendapi.family.controller.FamilyHandler
import org.pegasus.backendapi.family.service.FamilyInternalService
import org.pegasus.backendapi.family.service.FamilyService
import org.pegasus.backendapi.notification.EventPublisher
import org.pegasus.backendapi.transaction.service.TransactionInternalService
import org.pegasus.backendapi.user.service.UserInternalService
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.router
import java.util.*

val familyInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {

    bean {
        val entityManager = ref<EntityManager>()
        FamilyRepository(entityManager)
    }

    bean {
        val transactionService = ref<TransactionInternalService>()
        val familyRepository = ref<FamilyRepository>()
        val userService = ref<UserInternalService>()
        val eventPublisher = ref<EventPublisher>()

        FamilyService(transactionService, familyRepository, userService, eventPublisher)
    }

    bean<FamilyInternalService>()

    bean {
        val familyService = ref<FamilyService>()
        FamilyHandler(familyService)
    }

    bean {
        val familyHandler = ref<FamilyHandler>()

        router {
            "/api/v1/families".nest {
                POST("/") { _ ->
                    val response = familyHandler.create()
                    ServerResponse.ok().body(response)
                }
                POST("/invite/{id}") { request ->
                    val userId = UUID.fromString(request.pathVariable("id"))
                    val response = familyHandler.invite(userId)
                    ServerResponse.ok().body(response)
                }
                GET("/members") {
                    val members = familyHandler.members()
                    if (members.isEmpty()) {
                        ServerResponse.noContent().build()
                    } else {
                        ServerResponse.ok().body(members)
                    }
                }
                GET("/transactions") {
                    val transactions = familyHandler.transactions()
                    if (transactions.isEmpty()) {
                        ServerResponse.noContent().build()
                    } else {
                        ServerResponse.ok().body(transactions)
                    }
                }
            }
        }
    }

}
