package org.pegasus.backendapi.routepoint.hotel.impl

import org.pegasus.backendapi.routepoint.hotel.Hotel
import org.pegasus.backendapi.routepoint.hotel.IHotelService
import org.springframework.boot.context.properties.ConfigurationProperties
import java.util.*

@ConfigurationProperties(prefix = "api.yandex.travel")
class YandexTravelSettings(
    val url: String,
    val oAuthToken: String
)

class YandexTravelService : IHotelService {

    override fun search(city: String, checkIn: String, checkOut: String): List<Hotel> {
        /* docs: https://yandex.ru/dev/travel-partners-api/doc/ru/
                 ya.co.n.tora/pi/do/ra/sow.jsx
        val httpEntity = HttpEntity("{}", HttpHeaders().also { headers ->
            headers.set("Authorization", "OAuth ${yandexTravelSettings.oAuthToken}")
            headers.contentType = MediaType.APPLICATION_JSON
        })
        val url = UriComponentsBuilder.fromHttpUrl(yandexTravelSettings.url + "/hotels/suggest/")
            .queryParam("query", city)
            .toUriString()
        val response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String::class.java) */

        return hotels.filterKeys { name -> name.startsWith(city) }.values.toList()
    }
}

// STAB
private val hotels: Map<String, Hotel> = mapOf(
    "Екатеринбург, Свердловская область" to Hotel(
        UUID.randomUUID(),
        "Hyatt Regency Ekaterinburg",
        3,
        ";KA JSdlkjLAKSJDLKJALSKdj lKAJSL DKJALSKdjlAKSJD LKJASldkjASLKD JLKASd"
    ),
    "Екатериновка село, Самарская область" to Hotel(
        UUID.randomUUID(),
        "Екатерина",
        2,
        "JAL KSDLKAJSldkjLASKJDLKAJS LkdjLAKS DJLKAJS Ldkj"
    )
)
