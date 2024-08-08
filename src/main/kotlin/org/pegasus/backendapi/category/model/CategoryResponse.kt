package org.pegasus.backendapi.category.model

import java.time.Instant
import java.util.*

data class CategoryResponse(

    val id: UUID?,

    val name: String,

    val type: TransactionType,

    val description: String?,

    val created: Instant?,

    val update: Instant?

)
