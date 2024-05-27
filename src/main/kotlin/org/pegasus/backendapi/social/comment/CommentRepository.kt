package org.pegasus.backendapi.social.comment

import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import java.util.*

@Transactional
class CommentRepository(private val entityManager: EntityManager) {

    fun create(comment: Comment): Comment = comment.also {
        entityManager.persist(it)
    }

    fun findByDestinationId(destinationId: UUID): List<Comment> = entityManager
        .createQuery("SELECT c FROM Comment c WHERE c.destination.id = :destinationId", Comment::class.java)
        .setParameter("destinationId", destinationId)
        .resultList

}
