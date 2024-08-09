package org.pegasus.backendapi.category.model

import jakarta.persistence.*
import org.hibernate.annotations.UpdateTimestamp
import org.pegasus.backendapi.utils.IEntity
import java.time.Instant
import java.util.*

@Entity
@Table(name = "categories")
data class Category(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID = UUID.randomUUID(),

    var name: String,

    @Enumerated(EnumType.STRING)
    var type: TransactionType,

    var description: String? = null,

    val created: Instant = Instant.now(),

    @UpdateTimestamp
    val update: Instant = Instant.now(),

    @Version
    val version: Long = 0

) : IEntity
