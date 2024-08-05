package org.pegasus.backendapi.user.model

import java.time.Instant
import java.util.*

data class UserResponse(
    val id: UUID,
    val username: String,
    val email: String?,
    val isActive: Boolean,
    val verified: Boolean,
    val isAdmin: Boolean,
    val hasFamily: Boolean,
    val birthDate: Instant?,
    val created: Instant,
)
