package org.pegasus.backendapi.user

import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.security.JwtService
import org.pegasus.backendapi.user.model.UserResponse
import org.pegasus.backendapi.user.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(val userService: UserService, val jwtService: JwtService) {

    private val log = logger()

    @PostMapping
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        log.info { "new request $loginRequest" }
        val userDto = userService.find(loginRequest.username, loginRequest.password)
        return if (userDto == null) {
            ResponseEntity.notFound().build()
        } else {
            val jwt = jwtService.generateToken(userDto.username)
            val response = LoginResponse(jwt, UserMapper.toResponse(userDto))
            ResponseEntity.ok(response)
        }
    }

    data class LoginRequest(
        val username: String,
        val password: String
    )

    data class LoginResponse(
        val token: String,
        val user: UserResponse
    )

}
