package org.pegasus.backendapi.user

import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.user.model.UserResponse
import org.pegasus.backendapi.user.service.UserService
import java.util.*

class UserHandler(private val userService: UserService) {

    private val log = logger()

    fun user(id: UUID?): UserResponse? {
        val user = if (id == null) userService.currentUser() else userService.findUser(id)
        return user?.let { UserMapper.toResponse(it) }
    }

    fun all(): List<UserResponse> = userService.getAllUsers()
        .map { userDto -> UserMapper.toResponse(userDto) }

}
