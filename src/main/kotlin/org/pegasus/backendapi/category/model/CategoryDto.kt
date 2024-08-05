package org.pegasus.backendapi.category.model

import java.time.Instant
import java.util.*

data class CategoryDto(

    val id: UUID?,

    val name: String,

    val description: String?,

    val update: Instant? = null,

    val created: Instant? = null

)
