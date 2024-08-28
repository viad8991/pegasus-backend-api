package org.pegasus.backendapi.chat.model

import org.pegasus.backendapi.utils.IDto
import java.time.Instant
import java.util.*

data class MessageDto(

    val senderId: UUID,

    val body: String,

    val created: Instant

) : IDto
