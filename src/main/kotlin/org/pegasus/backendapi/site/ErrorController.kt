package org.pegasus.backendapi.site

import jakarta.servlet.http.HttpServletRequest
import org.apache.logging.log4j.kotlin.logger
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class CustomErrorController : ErrorController {

    private val log = logger()

    @RequestMapping("/error")
    fun handleError(request: HttpServletRequest, model: Model): String {

        model.addAttribute("statusCode", request.getAttribute("jakarta.servlet.error.status_code"))
        model.addAttribute("errorMessage", request.getAttribute("jakarta.servlet.error.message"))

        return "error"
    }

}