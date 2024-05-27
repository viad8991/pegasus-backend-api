package org.pegasus.backendapi.route

import jakarta.persistence.EntityManager
import org.pegasus.backendapi.route.destination.DestinationRepository
import org.pegasus.backendapi.route.trip.TripRepository
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans

val routeInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {

    bean {
        val entityManager = ref<EntityManager>()
        TripRepository(entityManager)
    }

    bean {
        val entityManager = ref<EntityManager>()
        DestinationRepository(entityManager)
    }

}
