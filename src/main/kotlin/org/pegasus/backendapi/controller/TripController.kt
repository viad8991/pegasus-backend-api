package org.pegasus.backendapi.controller

import org.pegasus.backendapi.model.entity.Trip
import org.pegasus.backendapi.service.TripService
import org.pegasus.backendapi.service.UserService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/trips")
class TripController(private val tripService: TripService, private val userService: UserService) {

    @PostMapping
    fun createTrip(@RequestBody tripRequest: TripRequest, auth: Authentication)/*: TripResponse*/ {
        /*return TripResponse.of(tripService.createTrip(tripRequest.name, auth.name))*/
    }

    @GetMapping
    fun getTrips(auth: Authentication): List<TripResponse> {
        val user = userService.findByUsername(auth.name) ?: throw RuntimeException("User not found")
        return tripService.getTripsByUserId(user.id).map { trip: Trip -> TripResponse.of(trip) }
    }

//    @PostMapping("/{tripId}/destinations")
//    fun addDestinationToTrip(
//        @PathVariable tripId: Long,
//        @RequestBody destinationRequest: DestinationRequest
//    ): TripResponse {
//        val destination = Destination(
//            city = destinationRequest.city,
//            startDate = destinationRequest.startDate,
//            endDate = destinationRequest.endDate,
//            trip = Trip(id = tripId)
//        )
//        return tripService.addDestinationToTrip(tripId, destination)
//    }
}

data class TripRequest(val name: String)
data class TripResponse(val name: String) {
    companion object {
        fun of(trip: Trip): TripResponse {
            return TripResponse(trip.id.toString())
        }
    }
}

data class DestinationRequest(val city: String, val startDate: String, val endDate: String)
