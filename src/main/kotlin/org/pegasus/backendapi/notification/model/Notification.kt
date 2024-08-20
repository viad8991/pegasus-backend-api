package org.pegasus.backendapi.notification.model

import io.hypersistence.utils.hibernate.type.json.JsonType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.persistence.Version
import org.hibernate.annotations.Type
import org.hibernate.annotations.UpdateTimestamp
import org.pegasus.backendapi.user.model.User
import org.pegasus.backendapi.utils.IEntity
import java.time.Instant
import java.util.*

@Entity
@Table(name = "notifications")
class Notification(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    val body: String,

    @ManyToOne(fetch = FetchType.LAZY)
    val recipient: User,

    @Type(JsonType::class)
    val metadata: Map<String, String> = emptyMap(),

    @Enumerated(EnumType.STRING)
    val status: NotificationStatus = NotificationStatus.NOT_READ,

    val created: Instant = Instant.now(),

    @UpdateTimestamp
    val update: Instant = Instant.now(),

    @Version
    val version: Long = 0

) : IEntity
