package org.pegasus.backendapi.category.model

import java.util.*

data class CategoryRequest(

    val id: UUID?,

    val name: String,

    val description: String?

)
