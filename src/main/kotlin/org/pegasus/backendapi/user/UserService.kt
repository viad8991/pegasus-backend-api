package org.pegasus.backendapi.user

import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.user.model.User
import org.pegasus.backendapi.user.model.UserDto
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

class UserService(private val userRepository: UserRepository) : UserDetailsService {

    private val log = logger()

    fun currentUser(): User {
        val user: Any? = SecurityContextHolder.getContext().authentication.principal

        return if (user == null || user !is User) {
            throw RuntimeException("FUCK Y")
        } else {
            user
        }
    }

    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByUsername(username) ?: throw RuntimeException("unauthorized")
    }

    fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }

    fun create(userDto: UserDto): UserDto {
        return UserMapper.toDto(userRepository.save(UserMapper.toEntity(userDto)))
    }

}