package org.pegasus.backendapi.category.model.request

import org.pegasus.backendapi.category.model.TransactionType
import java.util.*

data class CategoryRequest(

    val id: UUID,

    val name: String,

    val type: TransactionType,

    val description: String?

)
