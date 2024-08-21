package org.pegasus.backendapi.notification

import com.github.database.rider.core.api.dataset.DataSet
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.rsocket.metadata.WellKnownMimeType
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.apache.logging.log4j.kotlin.logger
import org.junit.jupiter.api.BeforeAll
import org.pegasus.backendapi.ApplicationTest
import org.pegasus.backendapi.security.JwtService
import org.springframework.boot.test.rsocket.server.LocalRSocketServerPort
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.messaging.rsocket.retrieveFlow
import org.springframework.security.rsocket.metadata.SimpleAuthenticationEncoder
import org.springframework.security.rsocket.metadata.UsernamePasswordMetadata
import org.springframework.util.MimeTypeUtils
import java.net.URI
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

@ApplicationTest
@DataSet(
    "dataset/yml/users.yml",
    "dataset/yml/notifications.yml",
    cleanBefore = true,
)
class NotificationHandlerTest(
    @LocalRSocketServerPort
    private val rSocketPort: Int,
    @MockkBean
    private val mockkJwtService: JwtService,
) {

    private val log = logger()

    private val token = "stub-user-token"
    private val username = "foo"

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
    }

    @Test
    fun `get notify for user`() {
        val actual = runBlocking {
            rSocketRequester.route("api.v1.notification").retrieveFlow<NotificationResponseTest>().first()
        }

        assertEquals("123e4567-e89b-12d3-a456-426614174000", actual.id.toString())
        assertEquals("test", actual.body)
    }

    data class NotificationResponseTest(
        val id: UUID,
        val body: String
    )

}
