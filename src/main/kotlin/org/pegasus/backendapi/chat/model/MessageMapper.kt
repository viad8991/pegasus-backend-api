package org.pegasus.backendapi.chat.model

import org.pegasus.backendapi.utils.IMapper
import java.time.Instant
import java.util.*

class MessageMapper {

    companion object : IMapper<MessageRequest, MessageDto, Message, MessageResponse> {
        override fun toDto(request: MessageRequest): MessageDto = MessageDto(
            senderId = UUID.randomUUID(), // TODO времянка
            body = request.body,
            created = Instant.now()
        )

        override fun toDto(entity: Message): MessageDto = MessageDto(
            senderId = entity.sender.id,
            body = entity.body,
            created = entity.created
        )

        override fun toResponse(dto: MessageDto): MessageResponse = MessageResponse(
            sender = dto.senderId,
            body = dto.body,
            created = dto.created
        )

    }

}
