package org.pegasus.backendapi.route.destination

import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import java.util.*

@Transactional
open class DestinationRepository(private val entityManager: EntityManager) {

    fun findById(destinationId: UUID): Destination? = entityManager.find(Destination::class.java, destinationId)

}
