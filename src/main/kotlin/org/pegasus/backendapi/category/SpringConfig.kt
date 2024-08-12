package org.pegasus.backendapi.category

import jakarta.persistence.EntityManager
import org.pegasus.backendapi.category.model.TransactionType
import org.pegasus.backendapi.category.model.request.CategoryCreateRequest
import org.pegasus.backendapi.category.service.CategoryInternalService
import org.pegasus.backendapi.category.service.CategoryService
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.reactive.function.server.coRouter
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.router
import java.util.*

val categoryInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {

    bean {
        val entityManager = ref<EntityManager>()
        CategoryRepository(entityManager)
    }

    bean {
        val categoryRepository = ref<CategoryRepository>()
        CategoryInternalService(categoryRepository)
    }

    bean {
        val categoryRepository = ref<CategoryRepository>()
        val categoryService = CategoryService(categoryRepository)
        val categoryHandler = CategoryHandler(categoryService)

        router {
            "/api/v1/category".nest {
                GET("/") { serverRequest ->
                    val type = serverRequest.param("type")
                        .map { TransactionType.valueOf(it) }
                        .orElse(null)
                    val response = categoryHandler.fetchByType(type)

                    if (response.isEmpty()) {
                        ServerResponse.noContent().build()
                    } else {
                        ServerResponse.ok().body(response)
                    }

                }
                POST("/") { serverRequest ->
                    val body = serverRequest.body(CategoryCreateRequest::class.java)
                    val response = categoryHandler.create(body)

                    ServerResponse.ok().body(response)
                }
                POST("/{id}") { serverRequest ->
                    val id = UUID.fromString(serverRequest.pathVariable("id"))
                    val body = serverRequest.body(CategoryCreateRequest::class.java)
                    val response = categoryHandler.update(id, body)

                    if (response == null) {
                        ServerResponse.noContent().build()
                    } else {
                        ServerResponse.ok().body(response)
                    }
                }
            }

        }
    }
}
