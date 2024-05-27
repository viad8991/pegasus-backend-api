package org.pegasus.backendapi.service

import jakarta.persistence.EntityManager
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

import org.pegasus.backendapi.ApplicationTest
import org.pegasus.backendapi.model.entity.User

@ApplicationTest
class UserServiceTest(private val userService: UserService) {

    @Test
    fun `full user service`() {
        val username = null
        val password = "password"
        val email = "email@test.org"

        val registerUser = userService.register(email, username, password)

        Assertions.assertThat(registerUser.id).isNotNull
        Assertions.assertThat(registerUser.username).isEqualTo(email)
        Assertions.assertThat(registerUser.email).isEqualTo(email)
        Assertions.assertThat(registerUser.password).startsWith("$")

        // И так конечно лучше не делать...
        val registerUserId = registerUser.id

        val userFindByName: User? = userService.findByUsername(email)
        Assertions.assertThat(userFindByName).isNotNull
        Assertions.assertThat(userFindByName?.id).isEqualTo(registerUserId)
        Assertions.assertThat(userFindByName?.username).isEqualTo(email)
        Assertions.assertThat(userFindByName?.password).startsWith("$")
        Assertions.assertThat(userFindByName?.email).isEqualTo(email)

        val userFindById: User? = userService.findById(registerUserId)
        Assertions.assertThat(userFindById).isNotNull
        Assertions.assertThat(userFindById?.id).isEqualTo(registerUserId)
        Assertions.assertThat(userFindById?.username).isEqualTo(username)
        Assertions.assertThat(userFindById?.password).startsWith("$")
        Assertions.assertThat(userFindById?.email).isEqualTo(email)

        val userFindByNameNotExist: User? = userService.findByUsername("foobar")
        Assertions.assertThat(userFindByNameNotExist).isNull()
    }
}
