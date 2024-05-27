package org.pegasus.backendapi.repository

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.persistence.EntityManager
import jakarta.persistence.NoResultException
import jakarta.transaction.Transactional
import org.pegasus.backendapi.model.Role
import org.pegasus.backendapi.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

open class UserRepository(private val entityManager: EntityManager) {

    private val log = KotlinLogging.logger { }

    @Transactional
    fun create(username: String, password: String, email: String): User {
        val user = User(username, password, email, Role.USER)
        entityManager.persist(user)
        return user
    }

    fun findByUsername(username: String): User? {
        return try {
            entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User::class.java)
                .setParameter("username", username)
                .singleResult
        } catch (ex: NoResultException) {
            log.debug(ex) { "cant not find user by username $username" }
            null
        }
    }

    fun findById(userId: UUID): User? = entityManager.find(User::class.java, userId)

}

@Repository
interface UserRepositoryJPA : JpaRepository<User, UUID> {
    fun findByUsername(username: String): User?
}
