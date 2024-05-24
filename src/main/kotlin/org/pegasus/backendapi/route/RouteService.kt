package org.pegasus.backendapi.route

import org.springframework.stereotype.Service
import org.springframework.util.RouteMatcher.Route
import java.util.*

@Service
class RouteService {
    fun createRoute(userId: UUID, destinations: List<Destination>, name: String?): Route {
        TODO("Not yet implemented")
    }

    fun getRoutesByUserId(userId: Long): List<Route> {
        TODO("Not yet implemented")
    }

    fun findById(routeId: UUID): Route? {
        TODO("Not yet implemented")
    }
}