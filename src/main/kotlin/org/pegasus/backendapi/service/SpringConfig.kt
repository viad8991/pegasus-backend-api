package org.pegasus.backendapi.service

import org.pegasus.backendapi.repository.TripRepository
import org.pegasus.backendapi.repository.UserRepository
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

val servicesInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {

    bean {
        val userRepository = ref<UserRepository>()
        val bCryptEncoder = ref<BCryptPasswordEncoder>()

        UserService(userRepository, bCryptEncoder)
    }

    bean {
        val tripRepository = ref<TripRepository>()

        TripService(tripRepository)
    }

}
