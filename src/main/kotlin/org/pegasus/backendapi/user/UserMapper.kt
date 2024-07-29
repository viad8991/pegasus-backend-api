package org.pegasus.backendapi.user

import org.pegasus.backendapi.transaction.model.UserRequest
import org.pegasus.backendapi.user.model.Role
import org.pegasus.backendapi.user.model.User
import org.pegasus.backendapi.user.model.UserDto
import org.pegasus.backendapi.user.model.UserResponse

class UserMapper {
    companion object {

        fun toDto(userRequest: UserRequest) = UserDto(
            userRequest.username,
        )

        fun toEntity(dto: UserDto) = User(
            username = dto.username,
            email = "123@mail.com",
            password = "123",
            role = Role.USER
        )

        fun toDto(entity: User) = UserDto(
            entity.username
        )

        fun toResponse(dto: UserDto) = UserResponse(
            dto.username
        )

    }
}
