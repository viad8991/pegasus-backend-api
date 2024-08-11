package org.pegasus.backendapi.category

import org.pegasus.backendapi.category.model.TransactionType
import org.pegasus.backendapi.category.model.request.CategoryCreateRequest
import org.pegasus.backendapi.category.service.CategoryInternalService
import org.pegasus.backendapi.category.service.CategoryService
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.router
import java.util.*

val categoryInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {

    bean {
        val categoryRepository = ref<CategoryRepository>()
        CategoryService(categoryRepository)
    }

    bean {
        val categoryRepository = ref<CategoryRepository>()
        CategoryInternalService(categoryRepository)
    }

    bean {
        val categoryService: CategoryService = ref<CategoryService>()
        val categoryController = CategoryController(categoryService)

        router {
            "/api/v1/category".nest {
                GET("/") { serverRequest ->
                    val type = serverRequest.param("type")
                        .map { TransactionType.valueOf(it) }
                        .orElse(null)
                    val response = categoryController.fetchByType(type)

                    if (response.isEmpty()) {
                        ServerResponse.noContent().build()
                    } else {
                        ServerResponse.ok().body(response)
                    }

                }
                POST("/") { serverRequest ->
                    val body = serverRequest.body(CategoryCreateRequest::class.java)
                    val response = categoryController.create(body)

                    ServerResponse.ok().body(response)
                }
                POST("/{id}") { serverRequest ->
                    val id = UUID.fromString(serverRequest.pathVariable("id"))
                    val body = serverRequest.body(CategoryCreateRequest::class.java)
                    val response = categoryController.update(id, body)

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
