package org.pegasus.backendapi.transaction.model

import org.pegasus.backendapi.category.model.request.CategoryRequest
import java.math.BigDecimal

data class CreateRequest(
    val amount: BigDecimal,
    val category: CategoryRequest? = null,
)
