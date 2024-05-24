package org.pegasus.backendapi.repository

import jakarta.persistence.EntityManager
import org.pegasus.backendapi.route.DestinationRepository
import org.pegasus.backendapi.route.TripRepository
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans

val repositoriesInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {

    bean<UserRepository> {
        val entityManager = ref<EntityManager>()
        UserRepository(entityManager)
    }

    bean {
        val entityManager = ref<EntityManager>()
        TripRepository(entityManager)
    }

    bean {
        val entityManager = ref<EntityManager>()
        DestinationRepository(entityManager)
    }
}
