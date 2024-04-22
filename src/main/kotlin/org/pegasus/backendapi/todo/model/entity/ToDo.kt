package org.pegasus.backendapi.todo.model.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import java.util.*

@Entity
class ToDo(
    @Id
    val id: UUID = UUID.randomUUID(),

    val name: String? = null,

    val userId: UUID? = null,

    @OneToMany(fetch = FetchType.LAZY)
    val tasks: List<Task> = emptyList(),

    @CreationTimestamp
    val create: Instant = Instant.now(),

    @UpdateTimestamp
    val update: Instant = Instant.now(),

    @Version
    val version: Long = 0,
)
