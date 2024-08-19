package org.pegasus.backendapi.notification.event

import org.pegasus.backendapi.notification.Event
import java.util.*

data class FamilyInviteEvent(
    val from: UUID,
    val fioFrom: String,
    val to: UUID,
) : Event("Вас приглашает $fioFrom вступить в группу")
