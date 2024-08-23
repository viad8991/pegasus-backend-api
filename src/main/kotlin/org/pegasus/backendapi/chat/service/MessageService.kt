package org.pegasus.backendapi.chat.service

import org.pegasus.backendapi.chat.MessageRepository
import org.pegasus.backendapi.chat.model.MessageDto
import org.pegasus.backendapi.chat.model.MessageMapper
import org.pegasus.backendapi.family.FamilyNotExistException
import org.pegasus.backendapi.family.service.FamilyInternalService
import org.pegasus.backendapi.user.service.UserInternalService

class MessageService(
    private val userService: UserInternalService,
    private val familyService: FamilyInternalService,
    private val messageRepository: MessageRepository,
) {

    fun findMessages(): List<MessageDto> {
        val currentUser = userService.currentUser()
        val familyId = currentUser.family?.id ?: throw FamilyNotExistException(currentUser.username)
        val messages = messageRepository.findByFamily(familyId)
        return messages.map { MessageMapper.toDto(it) }
    }

    fun create(request: MessageDto): MessageDto {
        val currentUser = userService.currentUser()
        // TODO охохохохо, 2 - throw FamilyNotExistException(currentUser.username)... not bad, not bad :/
        //  котлин хоть и решает проблему, но это все же проблема
        // val familyId = currentUser.family?.id ?: throw FamilyNotExistException(currentUser.username)
        // val family = familyService.findById(familyId) ?: throw FamilyNotExistException(currentUser.username)
        val family = currentUser.family?.id
            ?.let { familyId -> familyService.findById(familyId) }
            ?: throw FamilyNotExistException(currentUser.username)

        val message = messageRepository.create(currentUser, family, request.body)
        return MessageMapper.toDto(message)
    }

}
