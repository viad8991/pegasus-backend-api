package org.pegasus.backendapi.restservice.service.impl

import org.pegasus.backendapi.restservice.service.GreetingService
import org.slf4j.LoggerFactory

class GreetingServiceImpl : GreetingService {

    private val log = LoggerFactory.getLogger(GreetingServiceImpl::class.java)

    override fun createResponse(name: String): String {
        val response = "Hello ${name.replaceFirstChar { it.uppercase() }}!"
        log.debug("For name '$name' created response '$response'")
        return response
    }
}
