package org.pegasus.backendapi.controller

import org.pegasus.backendapi.model.entity.Trip
import org.pegasus.backendapi.service.TripService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import java.time.Instant
import java.util.*

@RestController
@RequestMapping("/api/v1/trips")
class TripController(private val tripService: TripService) {

    @PostMapping("create")
    fun createTrip(@RequestBody tripRequest: TripRequest, auth: Authentication): TripResponse {
        val trip = tripService.create(tripRequest, auth.name)
        return TripResponse.of(trip)
    }

    @GetMapping
    fun getTrips(auth: Authentication): ResponseEntity<List<TripResponse>> {
        val trips = tripService.getTripsByUserName(auth.name)
        return if (trips.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            return ResponseEntity.ok(trips.map { trip: Trip -> TripResponse.of(trip) })
        }
    }

    @PostMapping("/{tripId}/destinations")
    fun addDestinationToTrip(
        @PathVariable tripId: UUID,
        @RequestBody destinationRequest: DestinationRequest
    ): TripResponse {
        val trip = tripService.addDestinationToTrip(tripId, destinationRequest)
        return TripResponse.of(trip)
    }
}

data class TripRequest(val name: String)
data class TripResponse(val name: String) {
    companion object {
        fun of(trip: Trip): TripResponse {
            return TripResponse(trip.name)
        }
    }
}

data class DestinationRequest(val city: String, val startDate: Instant, val endDate: Instant)
