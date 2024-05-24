package org.pegasus.backendapi.model.entity

import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.Version
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import java.util.*

@MappedSuperclass
abstract class AbstractEntity {

    @Id
    val id: UUID = UUID.randomUUID()

    val created: Instant = Instant.now()

    @UpdateTimestamp
    val update: Instant = Instant.now()

    @Version
    val version: Long = 0
}
