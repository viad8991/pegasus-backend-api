package org.pegasus.backendapi.service

import org.pegasus.backendapi.model.entity.Destination
import org.pegasus.backendapi.model.entity.Trip
import org.pegasus.backendapi.repository.TripRepository
import java.util.UUID

class TripService(private val tripRepository: TripRepository) {

    fun createTrip(trip: String, name: String)/*: Trip*/ {
//        return tripRepository.create(trip)
    }

    fun getTripsByUserId(userId: UUID): Set<Trip> {
        return tripRepository.findByUserId(userId)
    }

    fun addDestinationToTrip(tripId: UUID, destination: Destination)/*: Trip*/ {
//        val trip = tripRepository.findById(tripId).orElseThrow { RuntimeException("Trip not found") }
//
//        val updatedDestinations = trip.destinations.toMutableList().apply { add(destination) }
//        val updatedTrip = trip.copy(destinations = updatedDestinations)
//
//        return tripRepository.update(updatedTrip)
    }
}