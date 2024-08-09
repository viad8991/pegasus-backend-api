package org.pegasus.backendapi.family.model.dto

import org.pegasus.backendapi.user.model.UserDto
import org.pegasus.backendapi.utils.IDto
import java.time.Instant
import java.util.*

class FamilyDto(
    val id: UUID,
    val members: List<UserDto>,
    val created: Instant,
    val update: Instant
) : IDto
