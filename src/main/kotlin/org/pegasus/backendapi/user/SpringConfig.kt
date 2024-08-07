package org.pegasus.backendapi.user

import org.pegasus.backendapi.user.service.UserService
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans

val userInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {

    bean<UserService>()

}