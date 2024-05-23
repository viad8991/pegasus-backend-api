package org.pegasus.backendapi.controller

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.pegasus.backendapi.ApplicationTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.http.*

@ApplicationTest
class AuthControllerTest(private val restTemplate: TestRestTemplate) {

    private final val baseV1ApiUrl = "/api/v1/auth"

    @Test
    fun register() {
        val httpHeaders = HttpHeaders().also { it.contentType = MediaType.APPLICATION_JSON }
        val httpEntity = HttpEntity("""{"username": "userA", "email": "userA@mail.ru", "password": "password"}""", httpHeaders)
        val url = "$baseV1ApiUrl/register"

        val response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String::class.java)

        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(response.body).isEqualTo("Hyatt Regency Ekaterinburg")
    }

    @Test
    fun login() {
        val url = "$baseV1ApiUrl/login"
        val httpEntity = HttpEntity("""{"username": "userA", "email": "userA@mail.ru", "password": "password"}""")

        val response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String::class.java)

        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(response.body).isEqualTo("Hyatt Regency Ekaterinburg")
    }
}
