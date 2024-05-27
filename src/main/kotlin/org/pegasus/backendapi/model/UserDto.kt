package org.pegasus.backendapi.model

import org.pegasus.backendapi.model.entity.User
import java.util.*

data class UserDto(val id: UUID, val email: String, val username: String, val role: Role) {
    companion object {
        fun from(user: User) = UserDto(user.id, user.email, user.username, user.role)
    }
}
