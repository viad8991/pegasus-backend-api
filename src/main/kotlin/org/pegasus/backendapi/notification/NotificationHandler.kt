package org.pegasus.backendapi.notification

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.pegasus.backendapi.notification.model.NotificationMapper
import org.pegasus.backendapi.notification.model.NotificationResponse
import org.pegasus.backendapi.notification.service.NotificationService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller

@Controller // чорт №1
@MessageMapping("api.v1") // чорт №2
class NotificationHandler(private val notificationService: NotificationService) {

    @MessageMapping("notification") // чорт №3
    suspend fun notification(): Flow<List<NotificationResponse>> = notificationService
        .notification()
        .map { value -> value.map { notify -> NotificationMapper.toResponse(notify) } }

}
