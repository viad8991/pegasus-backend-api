package org.pegasus.backendapi.notification.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.notification.NotificationRepository
import org.pegasus.backendapi.notification.event.FamilyInviteEvent
import org.pegasus.backendapi.notification.model.Notification
import org.pegasus.backendapi.notification.model.NotificationDto
import org.pegasus.backendapi.notification.model.NotificationMapper
import org.pegasus.backendapi.notification.model.NotificationStatus
import org.pegasus.backendapi.user.service.UserInternalService
import reactor.core.publisher.Flux
import java.util.*

class NotificationService(
    private val notificationRepository: NotificationRepository,
    private val userService: UserInternalService,
) {

    private val log = logger()

    fun notification(): Flux<NotificationDto> {
        val user = userService.currentUser()
        log.info { "get new notify for user $user" }
        val notReadableNotifications = notificationRepository.findByUserWithStatus(user, NotificationStatus.NOT_READ)
        if (notReadableNotifications.isEmpty()) {
            notReadableNotifications.plus(Notification(UUID.randomUUID(), "s8dfa78sd6f7a6d7f"))
        }
        return Flux.fromIterable(notReadableNotifications)
            .map { notification -> NotificationMapper.toDto(notification) }
    }

    fun create(event: FamilyInviteEvent) {
        val body: String = if (event.source is String) event.source as String else throw RuntimeException("")
        val metadata = mapOf("from" to event.from.toString(), "to" to event.to.toString())

        notificationRepository.create(body, metadata)
    }

}
