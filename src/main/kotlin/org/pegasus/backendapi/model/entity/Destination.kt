package org.pegasus.backendapi.model.entity

import jakarta.persistence.*
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import java.util.*

@Entity
@Table(name = "destinations")
data class Destination(

    @ManyToOne(fetch = FetchType.LAZY)
    val trip: Trip,

    val city: String,
    val startDate: Instant,
    val endDate: Instant

) {

    @Id
    val id: UUID = UUID.randomUUID()

    val created: Instant = Instant.now()

    @UpdateTimestamp
    val update: Instant = Instant.now()

    @Version
    val version: Long = 0
}
