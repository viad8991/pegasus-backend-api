package org.pegasus.backendapi.service

import org.pegasus.backendapi.model.entity.User
import org.pegasus.backendapi.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*

class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun register(email: String, username: String?, password: String): User {
        val encodedPassword = passwordEncoder.encode(password)
        return userRepository.create(username ?: email, encodedPassword, email)
    }

    fun findById(friendId: UUID): User? = userRepository.findById(friendId)

    fun findByUsername(username: String): User? = userRepository.findByUsername(username)

}
