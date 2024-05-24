package org.pegasus.backendapi.service

import org.pegasus.backendapi.route.TripRepository
import org.pegasus.backendapi.repository.UserRepository
import org.pegasus.backendapi.route.TripService
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
        val userService = ref<UserService>()

        TripService(tripRepository, userService)
    }

}
