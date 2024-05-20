package org.pegasus.backendapi.security

import jakarta.persistence.EntityManager
import org.pegasus.backendapi.model.entity.Role
import org.pegasus.backendapi.model.entity.User
import org.springframework.stereotype.Repository

@Repository
class UserRepository(val entityManager: EntityManager) {

    fun create(username: String, password: String, email: String): User {
        val user = User(username = username, password = password, email = email, role = Role.GUEST)
        entityManager.persist(user)
        return user
    }

    fun findByUsername(username: String): User? {
        return entityManager.createQuery("SELECT u FROM User WHERE u.username = :username", User::class.java)
            .setParameter("username", username)
            .singleResult
    }
}
