package org.pegasus.backendapi.chat.handler

import org.pegasus.backendapi.chat.model.MessageResponse
import org.pegasus.backendapi.chat.service.MessageService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import java.util.concurrent.Flow

@Controller
@MessageMapping("api.v1")
class SocketMessageHandler(private val messageService: MessageService) {

//    @MessageMapping("messages")
//    fun messages(): Flow<MessageResponse> = messageService.messages()

}
