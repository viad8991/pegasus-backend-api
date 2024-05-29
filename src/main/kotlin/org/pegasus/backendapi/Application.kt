package org.pegasus.backendapi

import org.pegasus.backendapi.repository.repositoriesInitializer
import org.pegasus.backendapi.route.routeInitializer
import org.pegasus.backendapi.routepoint.hotelInitializer
import org.pegasus.backendapi.security.securityInitializer
import org.pegasus.backendapi.service.servicesInitializer
import org.pegasus.backendapi.site.siteInitializer
import org.pegasus.backendapi.social.socialInitializer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
@ConfigurationPropertiesScan
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

object AppInitializer : ApplicationContextInitializer<GenericApplicationContext> {
    override fun initialize(applicationContext: GenericApplicationContext) {
        repositoriesInitializer.initialize(applicationContext)
        securityInitializer.initialize(applicationContext)
        servicesInitializer.initialize(applicationContext)

        routeInitializer.initialize(applicationContext)
        hotelInitializer.initialize(applicationContext)
        socialInitializer.initialize(applicationContext)

        siteInitializer(applicationContext).initialize(applicationContext)
    }
}
