package org.pegasus.backendapi.restservice

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class ErrorHandler {
    private val logger = KotlinLogging.logger {}

    suspend fun handleError(exception: Throwable, serverRequest: ServerRequest): ServerResponse {

        logger.error(exception) { exception.message }

        val statusCode = HttpStatus.BAD_REQUEST

        return ServerResponse.status(statusCode).bodyValueAndAwait("error")
    }
}