package org.pegasus.backendapi.category.model

import java.time.Instant
import java.util.*

data class CategoryDto(

    val id: UUID? = null,

    val name: String,

    val type: TransactionType,

    val description: String?,

    val update: Instant? = null,

    val created: Instant? = null

)
