package org.pegasus.backendapi.social.comment

import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne
import org.pegasus.backendapi.model.entity.AbstractEntity
import org.pegasus.backendapi.route.Destination
import org.pegasus.backendapi.model.entity.User

class Comment(

    @ManyToOne(fetch = FetchType.LAZY)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    val destination: Destination,

    val text: String,
    val rating: Int

) : AbstractEntity()