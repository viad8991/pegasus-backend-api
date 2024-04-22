package org.pegasus.backendapi.restservice.service.impl

import org.pegasus.backendapi.restservice.service.GreetingService
import org.slf4j.LoggerFactory
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

class GreetingServiceImpl : GreetingService {

    private val log = LoggerFactory.getLogger(GreetingServiceImpl::class.java)

    override suspend fun createResponse(name: String?): ServerResponse {
        val definitionName = name ?: "anon"
        val response = "Hello ${definitionName.replaceFirstChar { it.uppercase() }}!"
        log.debug("For name '$name' created response '$response'")
        return ServerResponse.ok().bodyValueAndAwait(response)
    }
}
