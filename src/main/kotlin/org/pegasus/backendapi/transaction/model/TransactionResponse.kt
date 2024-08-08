package org.pegasus.backendapi.transaction.model

import org.pegasus.backendapi.category.model.CategoryResponse
import java.math.BigDecimal
import java.time.Instant
import java.util.*

data class TransactionResponse(
    val id: UUID?,
    val amount: BigDecimal,
    val date: Instant?,
    val category: CategoryResponse? = null,
)
