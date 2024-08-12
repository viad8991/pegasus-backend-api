package org.pegasus.backendapi.auth

import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.security.JwtService
import org.pegasus.backendapi.user.UserMapper
import org.pegasus.backendapi.user.model.UserResponse
import org.pegasus.backendapi.user.service.UserService

class AuthController(private val userService: UserService, private val jwtService: JwtService) {

    private val log = logger()

    fun login(loginRequest: LoginRequest): LoginResponse? {
        val userDto = userService.find(loginRequest.username, loginRequest.password)
        return if (userDto == null) {
            null
        } else {
            val jwt = jwtService.generateToken(userDto.username)
            LoginResponse(jwt, UserMapper.toResponse(userDto))
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
