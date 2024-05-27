package org.pegasus.backendapi.service

import org.pegasus.backendapi.route.trip.TripRepository
import org.pegasus.backendapi.route.trip.TripService
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans

val servicesInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {

    bean {
//        val userRepository = ref<UserRepository>()
//        val passwordEncoder = ref<PasswordEncoder>()

        UserService(ref(), ref())
    }

    bean {
        val tripRepository = ref<TripRepository>()
        val userService = ref<UserService>()

        TripService(tripRepository, userService)
    }

}
