package org.pegasus.backendapi.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

// DOCS: https://github.com/jwtk/jjwt#installation

class JwtService(private val settings: SecuritySettings) {

    private val secretKey = Keys.hmacShaKeyFor(settings.salt.toByteArray())

    fun generateToken(subject: String): String {
        return Jwts.builder()
            .subject(subject)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + settings.expiration))
            .signWith(secretKey)
            .compact()
    }

    fun generateToken(userDetails: UserDetails): String {
        return Jwts.builder()
            .subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + settings.expiration))
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
