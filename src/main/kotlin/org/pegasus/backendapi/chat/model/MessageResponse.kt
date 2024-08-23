package org.pegasus.backendapi.chat.model

import org.pegasus.backendapi.utils.IResponse
import java.time.Instant
import java.util.*

data class MessageResponse(

    val body: String,

    val created: Instant,

    val sender: UUID

) : IResponse
