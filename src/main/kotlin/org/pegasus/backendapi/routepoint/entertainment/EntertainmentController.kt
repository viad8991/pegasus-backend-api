package org.pegasus.backendapi.routepoint.entertainment

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/entertainments")
class EntertainmentController(
    @Autowired private val entertainmentService: EntertainmentService
) {

    @GetMapping
    fun getEntertainments(@RequestParam city: String): ResponseEntity<List<EntertainmentResponse>> {
        val entertainments = entertainmentService.getEntertainmentsByCity(city)
        return if (entertainments.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok(entertainments.map { EntertainmentResponse.of(it) })
        }
    }
}

data class EntertainmentResponse(val id: Long, val name: String, val city: String) {
    companion object {
        fun of(entertainment: Entertainment) = EntertainmentResponse(
            entertainment.id,
            entertainment.name,
            entertainment.city
        )
    }
}
