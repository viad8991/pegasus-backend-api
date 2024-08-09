package org.pegasus.backendapi.family.model.response

import org.pegasus.backendapi.utils.Response
import java.time.Instant
import java.util.*

class FamilyResponse(
    val id: UUID,
    val update: Instant,
    val created: Instant,
) : Response
