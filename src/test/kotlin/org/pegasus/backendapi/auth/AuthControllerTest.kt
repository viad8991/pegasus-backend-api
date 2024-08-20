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

@DataSet("dataset/yml/users.yml", cleanBefore = true, strategy = SeedStrategy.CLEAN_INSERT)
@ApplicationTest
class AuthControllerTest(
    private val objectMapper: ObjectMapper,
    private val rest: TestRestTemplate,
) {

    @Test
    fun `success login with check token`() {
        val loginRequest = LoginTest("foobar", "123456")
        val response = rest.postForEntity("/api/v1/auth/", loginRequest, String::class.java)
        assertEquals(HttpStatus.OK, response.statusCode)

        val body = response.body
        assertNotNull(body)

        val node = objectMapper.readTree(body)
        assertNotNull(node["token"])

        val userNode = node["user"]
        assertNotNull(userNode)
        assertTrue(userNode.isObject)
        assertEquals("foobar", userNode["username"].asText())
    }

    @Test
    fun `error with default value from csv files`() {
        val loginRequest = LoginTest("admin", "admin")
        val response = rest.postForEntity("/api/v1/auth/", loginRequest, String::class.java)
        assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
    }

    private data class LoginTest(val username: String, val password: String)

}
