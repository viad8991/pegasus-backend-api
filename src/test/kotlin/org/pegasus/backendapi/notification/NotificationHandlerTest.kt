package org.pegasus.backendapi.notification

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.rsocket.metadata.WellKnownMimeType
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.reactor.asFlux
import kotlinx.coroutines.runBlocking
import org.apache.logging.log4j.kotlin.logger
import org.junit.jupiter.api.BeforeAll
import org.pegasus.backendapi.ApplicationTest
import org.pegasus.backendapi.notification.model.NotificationDto
import org.pegasus.backendapi.notification.model.NotificationStatus
import org.pegasus.backendapi.notification.service.NotificationService
import org.pegasus.backendapi.security.JwtService
import org.pegasus.backendapi.user.model.Role
import org.pegasus.backendapi.user.model.UserDto
import org.springframework.boot.test.rsocket.server.LocalRSocketServerPort
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.messaging.rsocket.retrieveFlow
import org.springframework.security.rsocket.metadata.SimpleAuthenticationEncoder
import org.springframework.security.rsocket.metadata.UsernamePasswordMetadata
import org.springframework.util.MimeTypeUtils
import java.net.URI
import java.time.Instant
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

@ApplicationTest
class NotificationHandlerTest(
    @LocalRSocketServerPort
    private val rSocketPort: Int,

    @MockkBean
    private val mockkJwtService: JwtService,

    @MockkBean
    private val mockkNotificationService: NotificationService
) {

    private val log = logger()

    private val token = "stub-user-token"
    private val username = "user"

    private val rSocketRequester: RSocketRequester = RSocketRequester
        .builder()
        .setupMetadata(
            UsernamePasswordMetadata(username, token),
            MimeTypeUtils.parseMimeType(WellKnownMimeType.MESSAGE_RSOCKET_AUTHENTICATION.string)
        )
        .rsocketStrategies { strategiesBuilder ->
            strategiesBuilder.encoders {
                it.add(SimpleAuthenticationEncoder())
                it.add(Jackson2JsonEncoder())
            }
            strategiesBuilder.decoders {
                it.add(Jackson2JsonDecoder())
            }
        }
        .websocket(URI.create("ws://localhost:$rSocketPort"))
        .also { rSocketRequester ->
            rSocketRequester.rsocket()?.also { rSocket ->
                rSocket
                    .onClose()
                    .doOnError { log.info(it) { "error ${it.message}" } }
                    .doFinally { log.info { "client disconnect" } }
                    .subscribe()
            }
        }

    @BeforeAll
    fun setUp() {
        every { mockkJwtService.extractUsername(token) } returns username
        every { mockkJwtService.validateToken(token, username) } returns true

        every { mockkNotificationService.notification() } returns flowOf(
            NotificationDto(
                UUID.fromString("123e4567-e89b-12d3-a456-426614174000"),
                "foo",
                NotificationStatus.NOT_READ,
                emptyMap(),
                UserDto(
                    UUID.randomUUID(),
                    "",
                    null,
                    Instant.now(),
                    true,
                    Instant.now(),
                    Instant.now(),
                    Role.USER,
                    true,
                    null
                )
            ),
        ).asFlux()
    }

    @Test
    fun `test foo`() = runBlocking {
        rSocketRequester
            .route("api.v1.notification")
            .retrieveFlow<NotificationResponseTest>()
            .collect { actual ->
                assertEquals("123e4567-e89b-12d3-a456-426614174000", actual.id.toString())
                assertEquals("foo", actual.body)
            }
    }

    data class NotificationResponseTest(
        val id: UUID,
        val body: String
    )

}
