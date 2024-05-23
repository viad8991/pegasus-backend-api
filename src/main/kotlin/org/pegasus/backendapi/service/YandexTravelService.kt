package org.pegasus.backendapi.service

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

//@ConstructorBinding
@ConfigurationProperties(prefix = "api.yandex.travel")
class YandexTravelSettings(
    val url: String,
    val oAuthToken: String
)

//TODO docs: https://yandex.ru/dev/travel-partners-api/doc/ru/

class YandexTravelService(
    private val yandexTravelSettings: YandexTravelSettings,
    private val restTemplate: RestTemplate
) {

    private val search = "/hotels/search/"
    private val suggest = "/hotels/suggest/"

    fun searchHotels(city: String, checkIn: String, checkOut: String): YandexResponse {
        val httpHeaders = HttpHeaders().also { headers ->
            headers.set("Authorization", "OAuth ${yandexTravelSettings.oAuthToken}")
            headers.contentType = MediaType.APPLICATION_JSON
        }
        val httpEntity = HttpEntity("{}", httpHeaders)

        val url = UriComponentsBuilder.fromHttpUrl(yandexTravelSettings.url + suggest)
            .queryParam("query", city)
            // .queryParam("geo_id", city)
            // .queryParam("checkin_date", checkIn)
            // .queryParam("checkout_date", checkOut)
            .toUriString()

        // val response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String::class.java)

        // TODO STUB
        return YandexResponse(
            listOf(
                YandexResponseRegion(54, "CITY", "Екатеринбург", "Свердловская область"),
                YandexResponseRegion(137511, "VILLAGE","Екатериновка","село, Самарская область")
            ),
            listOf(
                YandexResponseHotel(1262520952,  "Hyatt Regency Ekaterinburg",  "Гостиница · Свердловская область, Екатеринбург, улица Бориса Ельцина, 8"),
                YandexResponseHotel(130357884584, "Екатерина", "Гостиница · Кострома, улица Нижняя Дебря, 17",)
            )
        )
    }

}

data class YandexResponse(
    val region: List<YandexResponseRegion>,
    val hotel: List<YandexResponseHotel>,
)

data class YandexResponseRegion(
    val geoId: Int,
    val type: String,
    val name: String,
    val description: String
)

data class YandexResponseHotel(
    val hotelId: Long,
    val name: String,
    val description: String
)
