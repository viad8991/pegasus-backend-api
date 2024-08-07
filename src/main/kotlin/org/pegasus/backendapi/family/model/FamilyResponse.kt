package org.pegasus.backendapi.family.model

import java.time.Instant
import java.util.*

class FamilyResponse(
    val id: UUID,
    val update: Instant,
    val created: Instant,
)
