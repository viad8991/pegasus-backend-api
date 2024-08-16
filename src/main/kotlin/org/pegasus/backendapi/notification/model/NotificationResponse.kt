package org.pegasus.backendapi.notification.model

import org.pegasus.backendapi.utils.IResponse
import java.util.*

data class NotificationResponse(
    val id: UUID?,
    val body: String
) : IResponse
