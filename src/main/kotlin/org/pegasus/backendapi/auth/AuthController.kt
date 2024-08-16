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

    fun refresh(): String {
        return "";
//        try {
//            val username = jwtService.getUsernameFromRefreshToken(refreshToken)
//            val user = userService.loadUserByUsername(username)
//            if (jwtTokenProvider.validateRefreshToken(refreshToken, user)) {
//                val newAccessToken = jwtTokenProvider.createToken(user.username, user.roles)
//                return ResponseEntity.ok(mapOf("accessToken" to newAccessToken))
//            }
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid refresh token")
//        } catch (e: Exception) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error refreshing token")
//        }
    }

//    JwtTokenFilter
//    try {
//        val username = jwtTokenProvider.getUsernameFromRefreshToken(refreshToken)
//        val user = userService.loadUserByUsername(username)
//        if (jwtTokenProvider.validateRefreshToken(refreshToken, user)) {
//            val newAccessToken = jwtTokenProvider.createToken(user.username, user.roles)
//            return ResponseEntity.ok(mapOf("accessToken" to newAccessToken))
//        }
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid refresh token")
//    } catch (e: Exception) {
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error refreshing token")
//    }

//    JwtTokenProvider
//    fun shouldTokenBeRefreshed(token: String): Boolean {
//        val expirationDate = getExpirationDateFromToken(token)
//        val now = Date()
//        val diff = expirationDate.time - now.time
//        return diff < REFRESH_THRESHOLD // например, 5 минут
//    }
//
//    fun refreshToken(token: String): String {
//        val claims = getAllClaimsFromToken(token)
//        claims.issuedAt = Date()
//        return generateToken(claims)
//    }

    data class LoginRequest(
        val username: String,
        val password: String
    )

    data class LoginResponse(
        val token: String,
        val user: UserResponse
    )

}
