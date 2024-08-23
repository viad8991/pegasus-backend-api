package org.pegasus.backendapi.chat

import jakarta.persistence.EntityManager
import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.chat.model.Message
import org.pegasus.backendapi.family.model.entity.Family
import org.pegasus.backendapi.user.model.User
import java.util.*

class MessageRepository(private val entityManager: EntityManager) {

    private val log = logger()

    fun findByFamily(familyId: UUID): Set<Message> = entityManager
        // .createQuery("SELECT m FROM Message m WHERE m.family.id = :familyId", Message::class.java)
        .createQuery("SELECT m FROM Message m WHERE m.user.family.id = :familyId", Message::class.java)
        .setParameter("familyId", familyId)
        .resultList
        .toSet()

    fun create(sender: User, family: Family, body: String): Message {
        val message = Message(sender = sender, family = family, body = body)
        entityManager.persist(message)
        return message
    }

}
