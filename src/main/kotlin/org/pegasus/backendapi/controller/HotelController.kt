package org.pegasus.backendapi.controller

import org.pegasus.backendapi.service.YandexTravelService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/hotels")
class HotelController(private val yandexTravelService: YandexTravelService) {

    @GetMapping("/search")
    fun searchHotels(
        @RequestParam city: String,
        @RequestParam checkIn: String,
        @RequestParam checkOut: String
    ): String {
        return yandexTravelService.searchHotels(city, checkIn, checkOut).hotel.first().name
    }
}
