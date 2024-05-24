package org.pegasus.backendapi.routepoint.entertainment

class EntertainmentService {

    private val attractions = listOf(
        Attraction(id = 1, name = "Eiffel Tower", city = "Paris", description = "Iconic symbol of Paris."),
        Attraction(id = 2, name = "Louvre Museum", city = "Paris", description = "World's largest art museum."),
        Attraction(id = 3, name = "Colosseum", city = "Rome", description = "Ancient Roman gladiatorial arena.")
    )

    private val restaurants = listOf(
        Restaurant(id = 1, name = "Le Jules Verne", city = "Paris", cuisine = "French"),
        Restaurant(id = 2, name = "Osteria Francescana", city = "Modena", cuisine = "Italian"),
        Restaurant(id = 3, name = "El Celler de Can Roca", city = "Girona", cuisine = "Spanish")
    )

    fun getEntertainmentsByCity(city: String): List<Entertainment> {
        val cityLower = city.lowercase()

        val cityAttractions = attractions.filter { it.city.lowercase() == cityLower }
        val cityRestaurants = restaurants.filter { it.city.lowercase() == cityLower }

        return cityAttractions + cityRestaurants
    }
}
