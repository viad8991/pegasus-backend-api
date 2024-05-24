package org.pegasus.backendapi.routepoint.hotel

import java.util.*

interface IHotelService {
    fun search(city: String, checkIn: String, checkOut: String): List<Hotel>
}

data class Hotel(
    val id: UUID,
    val name: String,
    val start: Byte,
    val description: String,
)
