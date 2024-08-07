package org.pegasus.backendapi.transaction.model

import org.pegasus.backendapi.category.model.CategoryRequest
import java.math.BigDecimal

data class TransactionRequest(
    val type: TransactionType,
    val amount: BigDecimal,
    val category: CategoryRequest?,
)
