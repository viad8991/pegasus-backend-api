package org.pegasus.backendapi.transaction

import jakarta.persistence.EntityManager
import org.pegasus.backendapi.category.service.CategoryInternalService
import org.pegasus.backendapi.transaction.model.CreateRequest
import org.pegasus.backendapi.transaction.service.TransactionInternalService
import org.pegasus.backendapi.transaction.service.TransactionService
import org.pegasus.backendapi.user.service.UserInternalService
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.router

val transactionInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {

    bean {
        val entityManager = ref<EntityManager>()
        TransactionRepository(entityManager)
    }

    bean {
        val transactionRepository = ref<TransactionRepository>()

        TransactionInternalService(transactionRepository)
    }

    bean {
        val transactionRepository = ref<TransactionRepository>()
        val categoryService = ref<CategoryInternalService>()
        val userService = ref<UserInternalService>()

        TransactionService(transactionRepository, categoryService, userService)
    }

    bean {
        val transactionService = ref<TransactionService>()
        TransactionController(transactionService)
    }

    bean {
        val transactionController = ref<TransactionController>()

        router {
            "/api/v1/transactions".nest {
                GET("/") {
                    val transactions = transactionController.all()
                    if (transactions.isEmpty()) {
                        ServerResponse.noContent().build()
                    } else {
                        ServerResponse.ok().body(transactions)
                    }
                }
                POST("/") { request ->
                    val body = request.body(CreateRequest::class.java)
                    val response = transactionController.create(body)
                    ServerResponse.ok().body(response)
                }
            }
        }
    }

}
