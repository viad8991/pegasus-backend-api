package org.pegasus.backendapi.notification.model

import org.pegasus.backendapi.utils.IMapper

class NotificationMapper {

    companion object : IMapper<Nothing, NotificationDto, Notification, NotificationResponse> {

        override fun toDto(entity: Notification): NotificationDto = NotificationDto(
            id = entity.id,
            body = entity.body,
            status = entity.status,
            metadata = entity.metadata,
            recipient = entity.recipient.id,
        )

        override fun toResponse(dto: NotificationDto): NotificationResponse = NotificationResponse(
            id = dto.id,
            body = dto.body,
        )

    }

}
