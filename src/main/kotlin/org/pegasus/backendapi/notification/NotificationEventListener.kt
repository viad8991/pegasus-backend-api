package org.pegasus.backendapi.notification

import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.notification.event.FamilyInviteEvent
import org.pegasus.backendapi.notification.service.NotificationService
import org.springframework.context.event.EventListener

class NotificationEventListener(
    private val notificationService: NotificationService,
) {

    private val log = logger()

    @EventListener
    fun handleInviter(event: FamilyInviteEvent) {
        log.info { "new event $event" }
        notificationService.create(event)
    }

}