package org.pegasus.backendapi.notification.model

import org.pegasus.backendapi.user.UserMapper
import org.pegasus.backendapi.utils.IMapper

class NotificationMapper {

    companion object : IMapper<Nothing, NotificationDto, Notification, NotificationResponse> {

        override fun toEntity(dto: NotificationDto): Notification = Notification(
            recipient = UserMapper.toEntity(dto.recipient),
            body = dto.body,
            status = NotificationStatus.NOT_READ,
        )

        override fun toDto(entity: Notification): NotificationDto = NotificationDto(
            id = entity.id,
            recipient = UserMapper.toDto(entity.recipient),
            body = entity.body,
            status = entity.status,
            metadata = entity.metadata,
        )

        override fun toResponse(dto: NotificationDto): NotificationResponse = NotificationResponse(
            id = dto.id,
            body = dto.body
        )

    }

}
