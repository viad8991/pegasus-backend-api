package org.pegasus.backendapi.category.model

import org.pegasus.backendapi.utils.Dto
import java.time.Instant
import java.util.*

data class CategoryDto(

    val id: UUID? = null,

    val name: String,

    val type: TransactionType,

    val description: String? = null,

    val update: Instant? = null,

    val created: Instant? = null

) : Dto
