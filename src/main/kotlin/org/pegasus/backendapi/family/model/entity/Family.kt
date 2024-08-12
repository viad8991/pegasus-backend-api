package org.pegasus.backendapi.family.model.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.persistence.Version
import org.hibernate.annotations.UpdateTimestamp
import org.pegasus.backendapi.user.model.User
import org.pegasus.backendapi.utils.IEntity
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

) : IEntity
