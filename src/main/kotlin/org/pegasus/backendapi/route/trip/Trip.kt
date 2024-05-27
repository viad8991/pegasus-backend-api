package org.pegasus.backendapi.route.trip

import jakarta.persistence.*
import org.pegasus.backendapi.model.entity.AbstractEntity
import org.pegasus.backendapi.model.entity.User
import org.pegasus.backendapi.route.destination.Destination

@Entity
@Table(name = "trips")
class Trip(
    val name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    private val user: User,

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Destination::class)
    var destinations: List<Destination> = listOf()
) : AbstractEntity()
