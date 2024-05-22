package org.pegasus.backendapi.model.entity

import jakarta.persistence.*
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import java.util.*

@Entity
@Table(name = "trips")
class Trip(

    @Id
    val id: UUID = UUID.randomUUID(),

    private val name: String,

    @ManyToOne
    private val user: User,

    val email: String,

    @Enumerated(EnumType.STRING)
    val role: Role,

    val created: Instant = Instant.now(),
    @UpdateTimestamp
    val update: Instant = Instant.now(),
    @Version
    val version: Long = 0

)