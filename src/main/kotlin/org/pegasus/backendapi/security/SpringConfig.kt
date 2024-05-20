package org.pegasus.backendapi.security

import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

val securityInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {
    bean { UserRepository(ref()) }
    bean {
        val bCryptEncoder = BCryptPasswordEncoder()
        UserService(ref(), bCryptEncoder)
    }
}
