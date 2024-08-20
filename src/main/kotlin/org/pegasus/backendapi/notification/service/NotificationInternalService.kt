package org.pegasus.backendapi.notification.service

import org.pegasus.backendapi.notification.NotificationRepository
import org.pegasus.backendapi.notification.event.FamilyInviteEvent
import org.pegasus.backendapi.notification.model.Notification
import org.pegasus.backendapi.user.UserNotFoundException
import org.pegasus.backendapi.user.service.UserInternalService

class NotificationInternalService(
    private val userService: UserInternalService,
    private val notificationRepository: NotificationRepository,
) {

    fun create(event: FamilyInviteEvent): Notification {
        val body: String = if (event.source is String) event.source as String else throw RuntimeException("")
        val recipient = userService.findUser(event.to) ?: throw UserNotFoundException(event.to)
        val metadata = mapOf("from" to event.from.toString(), "to" to event.to.toString())

        return notificationRepository.create(body, recipient, metadata)
    }

}
