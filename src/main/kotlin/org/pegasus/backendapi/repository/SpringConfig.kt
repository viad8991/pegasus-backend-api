package org.pegasus.backendapi.repository

import jakarta.persistence.EntityManager
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans

val repositoriesInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {

    bean {
//        val entityManager = ref<EntityManager>()
        UserRepository(ref())
    }

}
