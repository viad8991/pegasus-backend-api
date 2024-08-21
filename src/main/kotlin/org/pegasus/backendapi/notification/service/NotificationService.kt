package org.pegasus.backendapi.notification.service

import jakarta.transaction.Transactional
import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.notification.NotificationRepository
import org.pegasus.backendapi.notification.model.NotificationDto
import org.pegasus.backendapi.notification.model.NotificationMapper
import org.pegasus.backendapi.notification.model.NotificationStatus
import org.pegasus.backendapi.user.service.UserInternalService
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class NotificationService(
    private val notificationRepository: NotificationRepository,
    private val userService: UserInternalService,
) {

    private val log = logger()

    // private val cashe = flow<NotificationDto> { while (isActive) { } }
    private val sinksByUser = ConcurrentHashMap<UUID, Sinks.Many<NotificationDto>>()

    fun notification(): Flux<NotificationDto> {
        val user = userService.currentUser()
        log.info { "get new notify for user $user" }
        val sinkByUser = sinksByUser.computeIfAbsent(user.id) { _ -> Sinks.many().replay().all() }

        notificationRepository.findByUserWithStatus(user, NotificationStatus.NOT_READ).forEach { notification ->
            sinkByUser.tryEmitNext(NotificationMapper.toDto(notification))
        }

        return sinkByUser
            .asFlux()
            .doOnSubscribe { _ -> log.info { "new subscriber" } }
            .doOnCancel { log.info { "unsubscribe" } }

    }

    fun addNew(dto: NotificationDto) {
        val sinkByUser = sinksByUser.computeIfAbsent(dto.recipient) { _ -> Sinks.many().replay().all() }
        sinkByUser.tryEmitNext(dto)
    }
}
