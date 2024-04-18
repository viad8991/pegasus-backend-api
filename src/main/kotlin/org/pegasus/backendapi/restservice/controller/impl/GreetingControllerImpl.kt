package org.pegasus.backendapi.restservice.controller.impl

import org.pegasus.backendapi.restservice.controller.GreetingController
import org.pegasus.backendapi.restservice.service.GreetingService
import org.springframework.web.bind.annotation.RequestParam

class GreetingControllerImpl(
    private val greetingService: GreetingService,
) : GreetingController {

    override fun greeting(@RequestParam name: String): String {
        return greetingService.createResponse(name)
    }

    override fun greeting(): String {
        return greetingService.createResponse("NoName")
    }
}
