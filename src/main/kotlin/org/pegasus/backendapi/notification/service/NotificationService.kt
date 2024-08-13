package org.pegasus.backendapi.notification.service

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.notification.NotificationRepository
import org.pegasus.backendapi.notification.model.NotificationDto
import org.pegasus.backendapi.notification.model.NotificationMapper
import org.pegasus.backendapi.user.service.UserInternalService
import kotlin.coroutines.CoroutineContext

class NotificationService(
    private val notificationRepository: NotificationRepository,
    private val userService: UserInternalService,
    override val coroutineContext: CoroutineContext
) : CoroutineScope {

    private val log = logger()

    suspend fun notification(): StateFlow<List<NotificationDto>> {
        val user = userService.currentUser()
        log.info { "new subscriber for notify is ${user.username}-${user.id}" }
        return flow {
            while (isActive) {
                val data = notificationRepository.findByUserNotReadable(user)
                val logStr = data.fold("") { acc, notification -> acc + "${notification.id}" }
                log.info { "new emit data $logStr" }
                emit(data.map { NotificationMapper.toDto(it) })
            }
        }.distinctUntilChanged().stateIn(this)

    }

}
