package org.pegasus.backendapi.notification.model

import org.pegasus.backendapi.user.model.UserDto
import org.pegasus.backendapi.utils.IDto
import java.util.*

data class NotificationDto(
    val id: UUID?,
    val body: String,
    val status: NotificationStatus,
    val metadata: Map<String, String>,
    val recipient: UserDto,
) : IDto
