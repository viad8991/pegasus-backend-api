package org.pegasus.backendapi.repository

import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.pegasus.backendapi.ApplicationTest
import org.pegasus.backendapi.model.entity.User

@ApplicationTest
@Transactional
class UserRepositoryTest(private val userRepository: UserRepository) {

    @Test
    // Так конечно лучше не делать...
    fun `full user repository`() {
        val username = "test"
        val password = "password"
        val email = "email@test.org"

        val userCreate: User = userRepository.create(username, password, email)

        Assertions.assertThat(userCreate.id).isNotNull
        Assertions.assertThat(userCreate.username).isEqualTo(username)
        Assertions.assertThat(userCreate.password).isEqualTo(password)
        Assertions.assertThat(userCreate.email).isEqualTo(email)

        // И так конечно лучше не делать...
        val userCreateId = userCreate.id

        val userFindByName: User? = userRepository.findByUsername(username)
        Assertions.assertThat(userFindByName).isNotNull
        Assertions.assertThat(userCreate.id).isEqualTo(userCreateId)
        Assertions.assertThat(userCreate.username).isEqualTo(username)
        Assertions.assertThat(userCreate.password).isEqualTo(password)
        Assertions.assertThat(userCreate.email).isEqualTo(email)

        val userFindById: User? = userRepository.findById(userCreateId)
        Assertions.assertThat(userFindById).isNotNull
        Assertions.assertThat(userFindById?.id).isEqualTo(userCreateId)
        Assertions.assertThat(userFindById?.username).isEqualTo(username)
        Assertions.assertThat(userFindById?.password).isEqualTo(password)
        Assertions.assertThat(userFindById?.email).isEqualTo(email)

        val userFindByNameNotExist: User? = userRepository.findByUsername("username")
        Assertions.assertThat(userFindByNameNotExist).isNull()
    }

}
