package org.pegasus.backendapi.repository

import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.pegasus.backendapi.model.entity.Trip
import java.util.*

@Transactional
class TripRepository(private val em: EntityManager) {
    fun findByUserId(userId: UUID): Set<Trip> {
        return em.createQuery("SELECT t FROM Trip WHERE i.user.id = :userId", Trip::class.java)
            .setParameter("userId", userId)
            .resultList
            .toSet()
    }

    fun findById(tripId: UUID): Trip {
        TODO("Not yet implemented")
    }
}
