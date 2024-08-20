package org.pegasus.backendapi.user

import jakarta.persistence.EntityManager
import jakarta.persistence.NoResultException
import jakarta.transaction.Transactional
import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.user.model.User
import org.pegasus.backendapi.user.model.UserDto
import java.util.*

@Transactional
class UserRepository(private val entityManager: EntityManager) {

    private val log = logger()

    fun findByUsername(username: String): User? = try {
        entityManager
            .createQuery("SELECT u FROM User u WHERE username = :username", User::class.java)
            .setParameter("username", username)
            .singleResult
    } catch (ex: NoResultException) {
        log.debug(ex) { "cant not found user by username $username" }
        null
    }

    fun findAll(): Set<User> = entityManager
        .createQuery("SELECT u FROM User u", User::class.java)
        .resultList
        .toSet()

    fun findById(id: UUID): User? = entityManager
        .find(User::class.java, id)

    fun create(dto: UserDto): User {
        val user = UserMapper.toEntity(dto)
        entityManager.persist(user)
        return user
    }

    fun update(user: User): User {
        return entityManager.merge(user)
    }

}
