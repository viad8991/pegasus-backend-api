package org.pegasus.backendapi.notification

import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.pegasus.backendapi.notification.model.Notification
import org.pegasus.backendapi.user.model.User

@Transactional
class NotificationRepository(private val entityManager: EntityManager) {

    fun findByUserNotReadable(user: User) = entityManager
        .createQuery("SELECT n FROM Notification n WHERE n.status = 'NOT_READ'", Notification::class.java)
        .resultList
        .toSet()

}
