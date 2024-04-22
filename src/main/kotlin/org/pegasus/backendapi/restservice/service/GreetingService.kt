package org.pegasus.backendapi.restservice.service

import org.springframework.web.reactive.function.server.ServerResponse

interface GreetingService {
    suspend fun createResponse(name: String?): ServerResponse
}
