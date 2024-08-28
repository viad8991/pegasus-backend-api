package org.pegasus.backendapi.chat.handler

import org.pegasus.backendapi.chat.model.MessageMapper
import org.pegasus.backendapi.chat.model.MessageRequest
import org.pegasus.backendapi.chat.model.MessageResponse
import org.pegasus.backendapi.chat.service.MessageService

class RestMessageHandler(private val messageService: MessageService) {

    fun message(message: MessageRequest): MessageResponse {
        val request = MessageMapper.toDto(message)
        val response = messageService.create(request)
        return MessageMapper.toResponse(response)
    }

    fun messages(): List<MessageResponse> = messageService
        .findMessages()
        .map { MessageMapper.toResponse(it) }

}
