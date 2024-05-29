package org.pegasus.backendapi.site

import jakarta.validation.Valid
import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.controller.AuthRequest
import org.pegasus.backendapi.controller.RegisterRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping


@Controller
class HtmlController {

    private val log = logger()

    private final val register: String = "register"
    private final val login: String = "login"
    private final val home: String = "home"

    @GetMapping("/register")
    fun registerForm(model: Model): String {
        model["registerRequest"] = RegisterRequest()
        return register
    }

    @PostMapping("/register")
    fun registerSubmit(@Valid @ModelAttribute registerRequest: RegisterRequest, model: Model): String {
        log.info { model.getAttribute("auth")?.toString() }

        model["registerRequest"] = registerRequest
        return login
    }

    @GetMapping("/login")
    fun loginForm(model: Model): String {
        model["auth"] = AuthRequest()
        return login
    }

    @PostMapping("/login")
    fun loginSubmit(@Valid @ModelAttribute authRequest: AuthRequest, model: Model): String {
        model["auth"] = authRequest
        return home
    }

    @RequestMapping(value = ["/"])
    fun home(): String {
        return home
    }
}
