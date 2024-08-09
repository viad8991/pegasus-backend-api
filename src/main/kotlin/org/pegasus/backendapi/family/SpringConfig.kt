package org.pegasus.backendapi.family

import org.pegasus.backendapi.family.service.FamilyInternalService
import org.pegasus.backendapi.family.service.FamilyService
import org.pegasus.backendapi.transaction.service.TransactionInternalService
import org.pegasus.backendapi.user.service.UserInternalService
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans

val familyInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {

    bean {
        val transactionService = ref<TransactionInternalService>()
        val familyRepository = ref<FamilyRepository>()
        val userService = ref<UserInternalService>()

        FamilyService(transactionService, familyRepository, userService)
    }

    bean<FamilyInternalService>()

}
