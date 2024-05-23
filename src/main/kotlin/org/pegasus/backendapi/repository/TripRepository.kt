package org.pegasus.backendapi.repository

import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.pegasus.backendapi.model.entity.Trip
import org.pegasus.backendapi.model.entity.User
import java.util.*

@Transactional
open class TripRepository(private val em: EntityManager) {

    fun findById(tripId: UUID): Trip? = em.find(Trip::class.java, tripId)

    fun findByUserId(userId: UUID): Set<Trip> {
        return em.createQuery("SELECT t FROM Trip WHERE i.user.id = :userId", Trip::class.java)
            .setParameter("userId", userId)
            .resultList
            .toSet()
    }

    fun create(name: String, user: User): Trip {
        val trip = Trip(name, user)
        em.persist(trip)
        return trip
    }

    fun update(trip: Trip): Trip {
        em.merge(trip)
        return trip
    }

}
