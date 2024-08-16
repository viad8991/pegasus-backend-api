package org.pegasus.backendapi.security

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.user.service.UserInternalService
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import reactor.core.publisher.Mono
import java.io.IOException

class JwtFilterManager(
    private val userService: UserInternalService,
    private val jwtService: JwtService
) : OncePerRequestFilter(), ReactiveAuthenticationManager {

    private val log = logger()

    private val authorizationHeaderName = "Authorization"

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwtFromHeader: String? = request.getHeader(authorizationHeaderName)

        if (jwtFromHeader != null) {
            validate(jwtFromHeader) { usernamePasswordAuthenticationToken ->
                usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
            }
        }

        filterChain.doFilter(request, response)
    }

    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        val jwtToken = authentication.credentials as String
        val authenticationToken = validate(jwtToken)
        return Mono.just(authenticationToken!!)
    }

    private fun validate(
        jwt: String,
        http: (UsernamePasswordAuthenticationToken) -> Unit = {},
    ): UsernamePasswordAuthenticationToken? {
        val username = jwtService.extractUsername(jwt)
        if (SecurityContextHolder.getContext().authentication != null) {
            return null
        }

        val userDetails = userService.loadUserByUsername(username)
        log.debug { "userDetails: $userDetails, userDetails.authorities ${userDetails.authorities}" }

        var authenticationToken: UsernamePasswordAuthenticationToken? = null
        if (jwtService.validateToken(jwt, username)) {
            authenticationToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
            authenticationToken.also { http(it) }
            SecurityContextHolder.getContext().authentication = authenticationToken
        }
        return authenticationToken
    }

}
