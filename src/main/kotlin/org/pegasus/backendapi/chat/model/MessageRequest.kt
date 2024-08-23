package org.pegasus.backendapi.chat.model

import org.pegasus.backendapi.utils.IRequest

data class MessageRequest(
    val body: String,
) : IRequest
