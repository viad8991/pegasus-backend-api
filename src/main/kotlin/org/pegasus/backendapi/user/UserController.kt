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

    @GetMapping("/{id}")
    fun user(@PathVariable(required = false) id: UUID?): ResponseEntity<UserResponse> {
        val foundUser = if (id == null) userService.currentUser() else userService.findUser(id)

        return if (foundUser == null) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok(UserMapper.toResponse(foundUser))
        }
    }

    @GetMapping
    fun all(): ResponseEntity<List<UserResponse>> = userService.getAllUsers()
        .map { userDto -> UserMapper.toResponse(userDto) }
        .let { ResponseEntity.ok(it) }
        .also {
            log.info { it }
        }

    @PostMapping("/login")
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
