package org.pegasus.backendapi.transaction.model

import org.pegasus.backendapi.category.model.CategoryDto
import java.math.BigDecimal
import java.time.Instant
import java.util.*

data class TransactionDto(
    val id: UUID? = null,
    val amount: BigDecimal,
    val created: Instant? = null,
    val category: CategoryDto? = null,
)
