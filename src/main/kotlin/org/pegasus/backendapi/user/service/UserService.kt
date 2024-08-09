package org.pegasus.backendapi.user.service

import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.user.UserMapper
import org.pegasus.backendapi.user.UserRepository
import org.pegasus.backendapi.user.model.User
import org.pegasus.backendapi.user.model.UserDto
import java.util.*

class UserService(
    private val internalService: UserInternalService,
    private val userRepository: UserRepository
) {

    private val log = logger()

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

    fun fetchCurrentUserData(): UserDto {
        return UserMapper.toDto(internalService.currentUser())
    }

}
