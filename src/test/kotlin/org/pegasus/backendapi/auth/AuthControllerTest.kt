package org.pegasus.backendapi.auth

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.database.rider.core.api.dataset.DataSet
import com.github.database.rider.core.api.dataset.SeedStrategy
import org.junit.jupiter.api.Test
import org.pegasus.backendapi.ApplicationTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@ApplicationTest
class AuthControllerTest(
    private val objectMapper: ObjectMapper,
    private val rest: TestRestTemplate,
) {

    @Test
    fun `success login with check token`() {
        val loginRequest = LoginTest("user", "123456")
        val response = rest.postForEntity("/api/v1/auth/", loginRequest, String::class.java)
        assertEquals(HttpStatus.OK, response.statusCode)

        val body = response.body
        assertNotNull(body)

        val node = objectMapper.readTree(body)
        assertNotNull(node["token"])

        val userNode = node["user"]
        assertNotNull(userNode)
        assertTrue(userNode.isObject)
        assertEquals("user", userNode["username"].asText())
    }

    @Test
    fun `error with default value from csv files`() {
        val loginRequest = LoginTest("admin", "admin")
        val response = rest.postForEntity("/api/v1/auth/", loginRequest, String::class.java)
        assertEquals(HttpStatus.OK, response.statusCode)
    }

    private data class LoginTest(val username: String, val password: String)

}
