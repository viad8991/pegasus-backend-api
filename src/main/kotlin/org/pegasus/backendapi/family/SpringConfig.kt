package org.pegasus.backendapi.family

import jakarta.persistence.EntityManager
import org.pegasus.backendapi.family.controller.FamilyHandler
import org.pegasus.backendapi.family.service.FamilyInternalService
import org.pegasus.backendapi.family.service.FamilyService
import org.pegasus.backendapi.transaction.service.TransactionInternalService
import org.pegasus.backendapi.user.service.UserInternalService
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.router

val familyInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {

    bean {
        val entityManager = ref<EntityManager>()
        FamilyRepository(entityManager)
    }

    bean {
        val transactionService = ref<TransactionInternalService>()
        val familyRepository = ref<FamilyRepository>()
        val userService = ref<UserInternalService>()

        FamilyService(transactionService, familyRepository, userService)
    }

    bean<FamilyInternalService>()

    bean {
        val familyService = ref<FamilyService>()
        FamilyHandler(familyService)
    }

    bean {
        val familyHandler = ref<FamilyHandler>()

        router {
            "/api/v1/family".nest {
                POST("/") { _ ->
                    val response = familyHandler.create()
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
