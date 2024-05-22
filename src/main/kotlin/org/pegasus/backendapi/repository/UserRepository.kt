package org.pegasus.backendapi.repository

import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.pegasus.backendapi.model.entity.Role
import org.pegasus.backendapi.model.entity.User


@Transactional
open class UserRepository(private val entityManager: EntityManager) {

    fun create(username: String, password: String, email: String): User {
        val user = User(username = username, password = password, email = email, role = Role.USER)
        entityManager.persist(user)
        return user
    }

    fun findByUsername(username: String): User? {
        return entityManager.createQuery("SELECT u FROM User WHERE u.username = :username", User::class.java)
            .setParameter("username", username)
            .singleResult
    }
}
