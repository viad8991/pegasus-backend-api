package org.pegasus.backendapi

import org.pegasus.backendapi.routepoint.hotelInitializer
import org.pegasus.backendapi.routepoint.hotel.impl.YandexTravelSettings
import org.pegasus.backendapi.repository.repositoriesInitializer
import org.pegasus.backendapi.security.securityInitializer
import org.pegasus.backendapi.service.servicesInitializer
import org.pegasus.backendapi.social.socialInitializer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
@EnableConfigurationProperties(
    YandexTravelSettings::class
)
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

object AppInitializer : ApplicationContextInitializer<GenericApplicationContext> {
    override fun initialize(applicationContext: GenericApplicationContext) {
        repositoriesInitializer.initialize(applicationContext)
        securityInitializer.initialize(applicationContext)
        servicesInitializer.initialize(applicationContext)

        hotelInitializer.initialize(applicationContext)
        socialInitializer.initialize(applicationContext)
    }
}
