package org.pegasus.backendapi.controller

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.pegasus.backendapi.ApplicationTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

@ApplicationTest
class HotelControllerTest(private val restTemplate: TestRestTemplate) {

    private final val baseApiUrl = "/api/v1/hotels"

    @Test
    fun yandex() {
        val response = restTemplate.getForEntity(
            "$baseApiUrl/search?city=Екатеринбург&checkIn=01&checkOut=02",
            String::class.java
        )

        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(response.body).isNotNull()
        Assertions.assertThat(response.body?.contains("Hyatt Regency Ekaterinburg"))
    }
}
