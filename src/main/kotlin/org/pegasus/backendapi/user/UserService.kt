package org.pegasus.backendapi.user

import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.user.model.User
import org.pegasus.backendapi.user.model.UserDto
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import java.util.*

class UserService(private val userRepository: UserRepository) : UserDetailsService {

    private val log = logger()

    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByUsername(username) ?: throw BadCredentialsException("unauthorized request")
    }

    fun currentUser(): UserDto {
        val user: Any? = SecurityContextHolder.getContext().authentication.principal
        return if (user == null || user !is User) {
            throw BadCredentialsException("unauthorized request")
        } else {
            UserMapper.toDto(user)
        }
    }

    fun find(username: String, password: String): UserDto? {
        return userRepository.findByUsername(username)?.let { UserMapper.toDto(it) }
    }

    fun getAllUsers(): List<UserDto> {
        return userRepository.findAll()
            .map { user: User -> UserMapper.toDto(user) }
    }

    fun create(userDto: UserDto): UserDto {
        return UserMapper.toDto(userRepository.save(UserMapper.toEntity(userDto)))
    }

    fun findUser(id: UUID): UserDto? {
        val foundUser = userRepository.findById(id)
        return if (foundUser.isPresent) UserMapper.toDto(foundUser.get()) else null
    }

}
