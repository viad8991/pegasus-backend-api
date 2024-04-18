package org.pegasus.backendapi.restservice.service

interface GreetingService {
    fun createResponse(name: String): String
}
