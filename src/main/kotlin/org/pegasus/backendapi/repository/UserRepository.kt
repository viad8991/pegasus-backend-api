package org.pegasus.backendapi.repository

import jakarta.persistence.EntityManager
import jakarta.persistence.NoResultException
import jakarta.transaction.Transactional
import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.model.Role
import org.pegasus.backendapi.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Transactional
class UserRepository(private val entityManager: EntityManager) {

    private val log = logger()

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
