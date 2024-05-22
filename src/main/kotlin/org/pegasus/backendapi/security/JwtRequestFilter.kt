package org.pegasus.backendapi.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.pegasus.backendapi.service.UserService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter

class JwtRequestFilter(
    private val jwtUtil: JwtUtil,
    private val userService: UserService,
) : OncePerRequestFilter() {

    private val authorizationHeaderName = "Authorization"
    private val bearerHeadValueStart = "Bearer "

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorizationHeader: String? = request.getHeader(authorizationHeaderName)

        var username: String? = null
        var jwt: String? = null

        if (authorizationHeader != null && authorizationHeader.startsWith(bearerHeadValueStart)) {
            jwt = authorizationHeader.replaceFirst(bearerHeadValueStart, "")
            username = jwtUtil.extractUsername(jwt)
        }

        if (username != null && jwt != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails = userService.findByUsername(username)

            if (jwtUtil.validateToken(jwt, username)) {
                val usPassAuth = UsernamePasswordAuthenticationToken(userDetails, null, emptyList())
                usPassAuth.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = usPassAuth
            }
        }

        filterChain.doFilter(request, response)
    }
}
