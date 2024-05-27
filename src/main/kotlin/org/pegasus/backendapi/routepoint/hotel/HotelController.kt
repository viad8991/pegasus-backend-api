package org.pegasus.backendapi.routepoint.hotel

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/v1/hotels")
class HotelController(private val yandexTravelService: IHotelService) {

    @GetMapping("/search")
    fun searchHotels(
        @RequestParam city: String,
        @RequestParam checkIn: String,
        @RequestParam checkOut: String
    ): ResponseEntity<List<HotelResponse>> {

        val externalServiceResponse = yandexTravelService.search(city, checkIn, checkOut)

        return if (externalServiceResponse.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok().body(externalServiceResponse.map { HotelResponse.of(it) })
        }
    }
}

data class HotelResponse(val id: UUID, val name: String, val start: Byte, val description: String) {
    companion object {
        fun of(hotel: Hotel) = HotelResponse(hotel.id, hotel.name, hotel.start, hotel.description)
    }
}
