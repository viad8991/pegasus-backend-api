package org.pegasus.backendapi.site

import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class HtmlController {

    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = "Blog"
        return "blog"
    }

}
