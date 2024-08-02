package org.pegasus.backendapi.user.model

import java.time.Instant
import java.util.*

data class UserResponse(
    val id: UUID,
    val username: String,
    val email: String?,
    val birthDate: Instant?,
    val documentsVerified: Boolean,
    val update: Instant,
    val created: Instant,
    val ru: String
)
