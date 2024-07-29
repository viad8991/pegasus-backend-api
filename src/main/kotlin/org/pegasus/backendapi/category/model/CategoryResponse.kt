package org.pegasus.backendapi.category.model

import java.util.*

data class CategoryResponse(

    val id: UUID,

    val name: String,

    val description: String?

)
