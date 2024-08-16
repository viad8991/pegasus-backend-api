package org.pegasus.backendapi.notification.model

import org.pegasus.backendapi.utils.IMapper

class NotificationMapper {

    companion object : IMapper<Nothing, NotificationDto, Notification, NotificationResponse> {

        override fun toEntity(dto: NotificationDto): Notification = Notification(
            status = NotificationStatus.NOT_READ,
            body = dto.body,
        )

        override fun toDto(entity: Notification): NotificationDto = NotificationDto(
            id = entity.id,
            body = entity.body
        )

        override fun toResponse(dto: NotificationDto): NotificationResponse = NotificationResponse(
            id = dto.id,
            body = dto.body
        )

    }

}
