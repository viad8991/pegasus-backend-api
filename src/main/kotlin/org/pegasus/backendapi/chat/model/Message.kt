package org.pegasus.backendapi.chat.model

import jakarta.annotation.Nonnull
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.pegasus.backendapi.family.model.entity.Family
import org.pegasus.backendapi.user.model.User
import org.pegasus.backendapi.utils.IEntity
import java.time.Instant
import java.util.*

@Entity
@Table(name = "messages")
class Message(

    @Id
    val id: UUID = UUID.randomUUID(),

    @ManyToOne(fetch = FetchType.LAZY)
    @Nonnull
    val sender: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @Nonnull
    val family: Family,

    @Nonnull
    val body: String,

    @Nonnull
    val created: Instant = Instant.now()

) : IEntity
