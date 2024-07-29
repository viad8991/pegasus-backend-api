package org.pegasus.backendapi.user

import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.security.JwtService
import org.pegasus.backendapi.user.model.User
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/user")
class UserController(val userService: UserService, val jwtService: JwtService) {

    private val log = logger()

    @GetMapping
    fun getAll(): List<User> = userService.getAllUsers()

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        return ResponseEntity.ok(
            LoginResponse(
                jwtService.generateToken(
                    userService.loadUserByUsername(loginRequest.username)
                )
            )
        )
    }

    data class LoginRequest(
        val username: String,
        val password: String
    )

    data class LoginResponse(
        val jwt: String
    )

}
