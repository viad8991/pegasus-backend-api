package org.pegasus.backendapi.route

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/routes")
class RouteController(
    @Autowired private val tripService: TripService
) {

    @GetMapping("/{tripId}")
    fun getRoute(@PathVariable tripId: Long): List<Destination> {
        val trip = tripService.findById(tripId)
        return trip.destinations
    }
}