package org.pegasus.backendapi.user

import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.security.JwtService
import org.pegasus.backendapi.user.model.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/user")
class UserController(val userService: UserService, val jwtService: JwtService) {

    private val log = logger()

    @GetMapping
    fun getAll(): List<UserResponse> = userService.getAllUsers()
        .map { userDto -> UserMapper.toResponse(userDto) }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        log.info { "new request $loginRequest" }
        val user = userService.find(loginRequest.username, loginRequest.password)
        return if (user == null) {
            ResponseEntity.notFound().build()
        } else {
            val jwt = jwtService.generateToken(user)
            val response = LoginResponse(user.id, jwt, user.username)
            ResponseEntity.ok(response)
        }
    }

    data class LoginRequest(
        val username: String,
        val password: String
    )

    data class LoginResponse(
        val id: UUID,
        val token: String,
        val username: String
    )

}
