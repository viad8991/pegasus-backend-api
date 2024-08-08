package org.pegasus.backendapi.category.model.request

import org.pegasus.backendapi.category.model.TransactionType

data class CategoryCreateRequest(

    val name: String,

    val type: TransactionType,

    val description: String? = null

)