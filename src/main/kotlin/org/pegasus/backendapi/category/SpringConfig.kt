package org.pegasus.backendapi.category

import org.pegasus.backendapi.category.service.CategoryInternalService
import org.pegasus.backendapi.category.service.CategoryService
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans

val categoryInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {

    bean {
        val categoryRepository = ref<CategoryRepository>()

        CategoryService(categoryRepository)
    }

    bean {
        val categoryRepository = ref<CategoryRepository>()

        CategoryInternalService(categoryRepository)
    }

}
