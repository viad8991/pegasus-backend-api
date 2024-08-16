package org.pegasus.backendapi.notification.service

import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.notification.NotificationRepository
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
            notReadableNotifications.plus(Notification(UUID.randomUUID(), NotificationStatus.NOT_READ, ";aksdjnkl"))
        }
        return Flux.fromIterable(notReadableNotifications)
            .map { notification -> NotificationMapper.toDto(notification) }
    }

}
