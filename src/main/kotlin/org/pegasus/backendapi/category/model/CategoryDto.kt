package org.pegasus.backendapi.category.model

import org.pegasus.backendapi.utils.IDto
import java.time.Instant
import java.util.*

data class CategoryDto(

    val id: UUID? = null,

    var name: String,

    var type: TransactionType,

    var description: String? = null,

    val update: Instant? = null,

    val created: Instant? = null

) : IDto
