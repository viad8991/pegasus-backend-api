package org.pegasus.backendapi.security

import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.pegasus.backendapi.model.entity.Role
import org.pegasus.backendapi.model.entity.User

interface UserRepository {
    fun create(username: String, password: String, email: String): User
    fun findByUsername(username: String): User?
}

@Transactional
open class JpaUserRepository(private val entityManager: EntityManager) : UserRepository {

    override fun create(username: String, password: String, email: String): User {
        val user = User(username = username, password = password, email = email, role = Role.ROLE_USER)
        entityManager.persist(user)
        return user
    }

    override fun findByUsername(username: String): User? {
        return entityManager.createQuery("SELECT u FROM User WHERE u.username = :username", User::class.java)
            .setParameter("username", username)
            .singleResult
    }
}
