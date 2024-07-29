package org.pegasus.backendapi.transaction.model

import jakarta.persistence.*
import org.hibernate.annotations.UpdateTimestamp
import org.pegasus.backendapi.category.model.Category
import org.pegasus.backendapi.user.model.User
import java.math.BigDecimal
import java.time.Instant
import java.util.*

@Entity
@Table(name = "transactions")
data class Transaction(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID = UUID.randomUUID(),

    val amount: BigDecimal,

    @Enumerated(EnumType.STRING)
    val type: TransactionType,

    @ManyToOne
    val category: Category,

    @ManyToOne
    val user: User,

    val date: Instant = Instant.now(),

    val created: Instant = Instant.now(),

    @UpdateTimestamp
    val update: Instant = Instant.now(),

    @Version
    val version: Long = 0
)
