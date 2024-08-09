package org.pegasus.backendapi.security

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.user.service.UserInternalService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

class JwtRequestFilter(
    private val userService: UserInternalService,
    private val jwtService: JwtService
) : OncePerRequestFilter() {

    private val log = logger()

    private val authorizationHeaderName = "Authorization"

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt: String? = request.getHeader(authorizationHeaderName)
        log.info { "jwt $jwt" }
        val username: String? = jwt?.let { jwtService.extractUsername(jwt) }

        if (username != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails = userService.loadUserByUsername(username)
            log.info { "userDetails: $userDetails, userDetails.authorities ${userDetails.authorities}" }

            if (jwtService.validateToken(jwt, username)) {
                val usPassAuth = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities).apply {
                    details = WebAuthenticationDetailsSource().buildDetails(request)
                }
                SecurityContextHolder.getContext().authentication = usPassAuth
            }
        }

        filterChain.doFilter(request, response)
    }
}
