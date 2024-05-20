package org.pegasus.backendapi.security

import org.pegasus.backendapi.model.entity.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun registerUser(email: String, username: String?, password: String): User {
        val encodedPassword = passwordEncoder.encode(password)
        return userRepository.create(username ?: email, encodedPassword, email)
    }

    fun findByUsername(username: String): User? = userRepository.findByUsername(username)

}