package org.pegasus.backendapi.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import java.util.*
import javax.crypto.SecretKey

// DOCS: https://github.com/jwtk/jjwt#installation

class JwtUtil {

    private val tenDaysInMills = 1000 * 60 * 60 * 10
    private val secretKey: SecretKey = Jwts.SIG.HS256.key().build()

    fun generateToken(username: String): String {

        return Jwts.builder()
            // .claims(claims)
            .subject(username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + tenDaysInMills))
            .signWith(secretKey)
            .compact()
    }

    fun extractUsername(token: String): String {
        return extractAllClaims(token).subject
    }

    private fun extractAllClaims(token: String): Claims = Jwts
        .parser().verifyWith(secretKey).build().parseSignedClaims(token).payload

    fun validateToken(token: String, username: String): Boolean {
        val extractedUsername = extractUsername(token)
        return (extractedUsername == username && !isTokenExpired(token))
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractAllClaims(token).expiration.before(Date())
    }
}
