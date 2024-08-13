package org.pegasus.backendapi.family

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.family.model.entity.Family
import java.util.*

@Transactional
class FamilyRepository(private val entityManager: EntityManager) {

    private val log = logger()

    fun create(): Family {
        val family = Family()
        entityManager.persist(family)
        return family
    }

    fun findById(id: UUID): Family? {
        return try {
            entityManager.find(Family::class.java, id)
        } catch (ex: EntityNotFoundException) {
            log.warn(ex) { "nor found to id $id" }
            null
        }
    }

}
