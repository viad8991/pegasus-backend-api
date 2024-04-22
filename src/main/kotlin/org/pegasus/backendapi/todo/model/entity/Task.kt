package org.pegasus.backendapi.todo.model.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Version
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import java.util.*

@Entity
class Task(
    @Id
    val id: UUID,

    val description: String,

    val from: Instant? = null,

    val to: Instant? = null,

    @CreationTimestamp
    val create: Instant = Instant.now(),

    @UpdateTimestamp
    val update: Instant = Instant.now(),

    @Version
    val version: Long = 0,
)
