package org.pegasus.backendapi.notification

import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.pegasus.backendapi.notification.model.Notification
import org.pegasus.backendapi.notification.model.NotificationStatus
import org.pegasus.backendapi.user.model.User

@Transactional
class NotificationRepository(private val entityManager: EntityManager) {

    fun findByUserWithStatus(user: User, status: NotificationStatus): Set<Notification> = entityManager
        .createQuery("SELECT n FROM Notification n WHERE n.status = :status", Notification::class.java)
        .setParameter("status", status)
        .resultList
        .toSet()

    fun create(body: String, metadata: Map<String, String>): Notification {
        val notification = Notification(body = body, metadata = metadata)
        entityManager.persist(notification)
        return notification
    }

}
