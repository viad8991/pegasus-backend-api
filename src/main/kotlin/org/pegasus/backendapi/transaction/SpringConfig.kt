package org.pegasus.backendapi.transaction

import org.pegasus.backendapi.category.service.CategoryInternalService
import org.pegasus.backendapi.transaction.service.TransactionInternalService
import org.pegasus.backendapi.transaction.service.TransactionService
import org.pegasus.backendapi.user.service.UserInternalService
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans

val transactionInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {

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

}
