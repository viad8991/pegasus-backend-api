package org.pegasus.backendapi

import jakarta.persistence.EntityManagerFactory
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

@ApplicationTest
class DatabaseInfrastructureTest(private val enf: EntityManagerFactory) {
    @Test
    fun `database infrastructure test`() {
        val em = enf.createEntityManager()
        assertTrue(em.isOpen)
        em.close()
    }
}
