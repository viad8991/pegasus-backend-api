package org.pegasus.backendapi.transaction.model

import java.math.BigDecimal
import java.time.Instant
import java.util.*

data class TransactionResponse(
    val id: UUID?,
    val type: TransactionType,
    val amount: BigDecimal,
    val date: Instant?,
    val categoryId: UUID,
)
