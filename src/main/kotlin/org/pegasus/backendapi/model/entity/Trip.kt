package org.pegasus.backendapi.model.entity

import jakarta.persistence.*
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import java.util.*

@Entity
@Table(name = "trips")
data class Trip(
    val name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    private val user: User,

    @OneToMany(fetch = FetchType.LAZY)
    val destinations: List<Destination> = listOf()
) {

    @Id
    val id: UUID = UUID.randomUUID()

    val created: Instant = Instant.now()

    @UpdateTimestamp
    val update: Instant = Instant.now()

    @Version
    val version: Long = 0

    override fun toString(): String {
        return "Trip{name=$name, user=${user.username}, destinationsCount=${destinations.size}}"
    }
}
