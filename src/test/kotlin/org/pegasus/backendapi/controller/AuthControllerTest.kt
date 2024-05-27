package org.pegasus.backendapi.controller

import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.assertj.core.api.Assertions
import org.pegasus.backendapi.ApplicationTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import kotlin.test.Test

@ApplicationTest
@Transactional
class AuthControllerTest(private val restTemplate: TestRestTemplate) {

    private final val baseV1ApiUrl = "/api/v1/auth"
    private final val body = """{"username": "userA", "email": "userA@mail.ru", "password": "password"}"""

    @Test
    fun register() {
        val httpEntity = HttpEntity(body, HttpHeaders().also { it.contentType = MediaType.APPLICATION_JSON })
        val url = "$baseV1ApiUrl/register"
        val response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String::class.java)

        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(response.body).isEqualTo("User userA registered successfully")
    }

    @Test
    fun login() {
        val url = "$baseV1ApiUrl/login"
        val httpEntity = HttpEntity(body, HttpHeaders().also { it.contentType = MediaType.APPLICATION_JSON })
        val response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String::class.java)

        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(response.body).isEqualTo("Hyatt Regency Ekaterinburg")
    }
}
