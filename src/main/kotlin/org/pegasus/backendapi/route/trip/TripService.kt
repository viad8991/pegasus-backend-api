package org.pegasus.backendapi.route.trip

import org.pegasus.backendapi.route.destination.Destination
import org.pegasus.backendapi.service.UserService
import java.util.*

class TripService(
    private val tripRepository: TripRepository,
    private val userService: UserService
) {

    fun create(tripRequest: TripRequest, userName: String): Trip {
        val user = userService.findByUsername(userName) ?: throw RuntimeException("Пользователь не найден")
        return tripRepository.create(tripRequest.name, user)
    }

    fun getTripsByUserName(userName: String): Set<Trip> {
        val user = userService.findByUsername(userName) ?: throw RuntimeException("Пользователь не найден")
        return tripRepository.findByUserId(user.id)
    }

    fun addDestinationToTrip(tripId: UUID, destinRequest: DestinationRequest): Trip {
        val trip = tripRepository.findById(tripId) ?: throw RuntimeException("Trip not found")
        val destination = Destination(trip, destinRequest.city, destinRequest.startDate, destinRequest.startDate)

        val updatedDestinations = trip.destinations.toMutableList().apply { add(destination) }

        // val updatedTrip = trip.copy(destinations = updatedDestinations)
        trip.destinations = updatedDestinations

        return tripRepository.update(trip)
    }

    fun findById(tripId: Long): Trip {
        TODO("Not yet implemented")
    }
}