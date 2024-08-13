package org.pegasus.backendapi

import org.pegasus.backendapi.auth.authInitializer
import org.pegasus.backendapi.category.categoryInitializer
import org.pegasus.backendapi.chat.chatInitializer
import org.pegasus.backendapi.family.familyInitializer
import org.pegasus.backendapi.notification.notificationInitializer
import org.pegasus.backendapi.security.SecuritySettings
import org.pegasus.backendapi.security.securityInitializer
import org.pegasus.backendapi.transaction.transactionInitializer
import org.pegasus.backendapi.user.userInitializer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableConfigurationProperties(SecuritySettings::class)
@SpringBootApplication
@EnableTransactionManagement
@ConfigurationPropertiesScan
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

object AppInitializer : ApplicationContextInitializer<GenericApplicationContext> {
    override fun initialize(applicationContext: GenericApplicationContext) {
        userInitializer.initialize(applicationContext)
        authInitializer.initialize(applicationContext)

        securityInitializer.initialize(applicationContext)

        categoryInitializer.initialize(applicationContext)
        familyInitializer.initialize(applicationContext)
        transactionInitializer.initialize(applicationContext)

        chatInitializer.initialize(applicationContext)
        notificationInitializer.initialize(applicationContext)
    }
}
