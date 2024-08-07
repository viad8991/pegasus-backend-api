package org.pegasus.backendapi.family.model

import org.pegasus.backendapi.user.model.UserDto
import java.time.Instant
import java.util.*

class FamilyDto(
    val id: UUID,
    val members: List<UserDto>,
    val created: Instant,
    val update: Instant
)
