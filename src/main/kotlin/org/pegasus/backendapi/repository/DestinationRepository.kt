package org.pegasus.backendapi.repository

import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional

@Transactional
class DestinationRepository(private val em: EntityManager) {

}
