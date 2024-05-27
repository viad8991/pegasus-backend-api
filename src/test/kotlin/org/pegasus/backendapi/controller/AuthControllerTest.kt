package org.pegasus.backendapi.controller

import jakarta.persistence.EntityManager
import org.assertj.core.api.Assertions
import org.assertj.db.type.Changes
import org.assertj.db.type.Table
import org.junit.jupiter.api.Test
import org.pegasus.backendapi.ApplicationTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import org.springframework.jdbc.datasource.DataSourceUtils
import org.springframework.jdbc.datasource.SingleConnectionDataSource
import org.springframework.orm.jpa.EntityManagerFactoryInfo

@ApplicationTest
class AuthControllerTest(
    private val restTemplate: TestRestTemplate,
    private val entityManager: EntityManager
) {

    private final val baseV1ApiUrl = "/api/v1/auth"
    private final val body = """{"username": "userA", "email": "userA@mail.ru", "password": "password"}"""

    @Test
    fun register() {
        val connection = DataSourceUtils.getConnection((entityManager.entityManagerFactory as EntityManagerFactoryInfo).dataSource!!)
        val table = Table(SingleConnectionDataSource(connection, true), "users")

        val changes = Changes(table)
        changes.setStartPointNow()

        val httpEntity = HttpEntity(body, HttpHeaders().also { it.contentType = MediaType.APPLICATION_JSON })
        val url = "$baseV1ApiUrl/register"
        val  response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String::class.java)
//        entityManager.flush()

        changes.setEndPointNow()

        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(response.body).isEqualTo("Hyatt Regency Ekaterinburg")
    }

    @Test
    fun login() {
        val connection = DataSourceUtils.getConnection((entityManager.entityManagerFactory as EntityManagerFactoryInfo).dataSource!!)
        val table = Table(SingleConnectionDataSource(connection, true), "users")

        val changes = Changes(table)
        changes.setStartPointNow()

        val url = "$baseV1ApiUrl/login"
        val httpEntity = HttpEntity(body, HttpHeaders().also { it.contentType = MediaType.APPLICATION_JSON })

        val response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String::class.java)
        entityManager.flush()

        changes.setEndPointNow()
        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(response.body).isEqualTo("Hyatt Regency Ekaterinburg")
    }
}
