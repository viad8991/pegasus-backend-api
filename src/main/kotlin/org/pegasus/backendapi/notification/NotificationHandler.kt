package org.pegasus.backendapi.notification

import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.notification.model.NotificationMapper
import org.pegasus.backendapi.notification.model.NotificationResponse
import org.pegasus.backendapi.notification.service.NotificationService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux

@Controller // чорт №1
@MessageMapping("api.v1") // чорт №2
class NotificationHandler(
    private val notificationService: NotificationService,
    // @Qualifier(value = "notificationDispatcher")
    // override val coroutineContext: CoroutineContext,
) /*: CoroutineScope*/ {

    private val log = logger()

    @MessageMapping("notification") // чорт №3
    fun notification(): Flux<NotificationResponse> {
        log.info { "new subscriber for notify" }
        return notificationService.notification()
            .map(NotificationMapper::toResponse)
            .doOnNext { log.info { "resp $it" } }
    }

}
