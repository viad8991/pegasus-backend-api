package org.pegasus.backendapi.restservice

import org.pegasus.backendapi.restservice.controller.GreetingController
import org.pegasus.backendapi.restservice.controller.impl.GreetingControllerImpl
import org.pegasus.backendapi.restservice.service.GreetingService
import org.pegasus.backendapi.restservice.service.impl.GreetingServiceImpl
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans

val greetingInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {
    bean<GreetingService> { GreetingServiceImpl() }
    bean<GreetingController> { GreetingControllerImpl(ref()) }
}
