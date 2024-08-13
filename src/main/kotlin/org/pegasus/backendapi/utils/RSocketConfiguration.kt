package org.pegasus.backendapi.utils

import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.messaging.rsocket.annotation.support.RSocketMessageHandler

val rSocketConfigurationInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {

    bean {
        val messageHandler = RSocketMessageHandler()
        messageHandler.rSocketStrategies = ref()

        messageHandler
    }

}
