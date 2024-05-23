package org.pegasus.backendapi.controller

import org.pegasus.backendapi.security.JwtUtil
import org.pegasus.backendapi.service.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val userService: UserService,
    private val jwtUtil: JwtUtil,
    private val authenticationManager: AuthenticationManager
) {

    @PostMapping("/register")
    fun register(@RequestBody register: RegisterRequest): String {
        val newUser = userService.registerUser(register.email, register.username, register.password)
        return "User ${newUser.username} registered successfully"
    }

    @PostMapping("/login")
    fun login(@RequestBody request: AuthRequest): AuthResponse {
        try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    request.username,
                    request.password
                )
            )
        } catch (e: AuthenticationException) {
            throw RuntimeException("Invalid credentials")
        }

        val token = jwtUtil.generateToken(request.username)
        return AuthResponse(token)
    }
}

data class RegisterRequest(val username: String?, val email: String, val password: String)

data class AuthRequest(val username: String, val password: String)
data class AuthResponse(val token: String)
