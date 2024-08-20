package org.pegasus.backendapi

import com.github.database.rider.core.api.configuration.DBUnit
import com.github.database.rider.core.connection.RiderDataSource
import com.github.database.rider.spring.api.DBRider
import io.zonky.test.db.AutoConfigureEmbeddedDatabase
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor

@SpringBootTest(
    classes = [Application::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("test")

@DBRider
@DBUnit(
    expectedDbType = RiderDataSource.DBType.POSTGRESQL,
    cacheConnection = true,
    caseSensitiveTableNames = true,
    schema = "public"
)
@AutoConfigureEmbeddedDatabase(
    provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY,
    type = AutoConfigureEmbeddedDatabase.DatabaseType.POSTGRES
)

@Target(AnnotationTarget.CLASS)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
annotation class ApplicationTest
