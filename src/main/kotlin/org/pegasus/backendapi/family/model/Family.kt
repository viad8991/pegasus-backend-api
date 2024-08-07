package org.pegasus.backendapi.family.model

import jakarta.persistence.*
import org.hibernate.annotations.UpdateTimestamp
import org.pegasus.backendapi.user.model.User
import java.time.Instant
import java.util.*

@Entity
@Table(name = "families")
class Family(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID = UUID.randomUUID(),

    @OneToMany(mappedBy = "family", fetch = FetchType.LAZY)
    val members: MutableSet<User> = mutableSetOf(),

    val created: Instant = Instant.now(),

    @UpdateTimestamp
    val update: Instant = Instant.now(),

    @Version
    val version: Long = 0

)
