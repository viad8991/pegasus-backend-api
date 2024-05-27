package org.pegasus.backendapi.social.share

import org.pegasus.backendapi.route.RouteService
import org.pegasus.backendapi.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/v1/share")
class ShareRouteController(
    @Autowired private val routeService: RouteService,
    @Autowired private val userService: UserService
) {

    @PostMapping
    fun shareRoute(@RequestBody request: ShareRouteRequest) {
        val route = routeService.findById(request.routeId)
        val friend = userService.findById(request.friendId)
    }
}

data class ShareRouteRequest(
    val routeId: UUID,
    val friendId: UUID
)
