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
            id = entity.id,
            username = entity.username,
            email = entity.email,
            birthDate = entity.birthDate,
            documentsVerified = entity.documentsVerified,
            update = entity.update,
            created = entity.created,
            active = entity.isEnabled,
            role = entity.role,
            family = entity.family?.id,
        )

        fun toResponse(dto: UserDto) = UserResponse(
            id = dto.id,
            username = dto.username,
            email = dto.email,
            isActive = dto.active,
            verified = dto.documentsVerified,
            isAdmin = dto.role == Role.ADMIN,
            hasFamily = dto.family != null,
            birthDate = dto.birthDate,
            created = dto.created
        )
    }
}
