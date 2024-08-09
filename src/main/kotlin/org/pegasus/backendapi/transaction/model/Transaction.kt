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

    @ManyToOne
    val user: User,

    @ManyToOne
    val category: Category? = null,

    val created: Instant = Instant.now(),

    @UpdateTimestamp
    val update: Instant = Instant.now(),

    @Version
    val version: Long = 0

)
