package org.pegasus.backendapi.user

import org.pegasus.backendapi.user.model.Role
import org.pegasus.backendapi.user.model.User
import org.pegasus.backendapi.user.model.UserDto
import org.pegasus.backendapi.user.model.UserResponse

class UserMapper {
    companion object {

        fun toEntity(dto: UserDto) = User(
            username = dto.username,
            email = "123@mail.com",
            password = "123",
            role = Role.USER
        )

        fun toDto(entity: User) = UserDto(
            entity.id,
            entity.username,
            entity.email,
            entity.birthDate,
            entity.documentsVerified,
            entity.update,
            entity.created,
            entity.role.ru
        )

        fun toResponse(dto: UserDto) = UserResponse(
            dto.id,
            dto.username,
            dto.email,
            dto.birthDate,
            dto.documentsVerified,
            dto.update,
            dto.created,
            dto.ru
        )

    }
}
