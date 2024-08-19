package org.pegasus.backendapi.notification

import org.apache.logging.log4j.kotlin.logger
import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

abstract class Event(body: String) : ApplicationEvent(body)

@Service
class EventPublisher(val applicationEventPublisher: ApplicationEventPublisher) {

    private val log = logger()

    fun publish(event: Event) {
        log.info { "publish new event $event" }
        applicationEventPublisher.publishEvent(event)
    }

}
