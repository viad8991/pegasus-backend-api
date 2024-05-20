package org.pegasus.backendapi.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtils(
    @Value("\${jwt.secret}") private val secret: String
) {
    fun generateToken(username: String): String {
        val claims: Map<String, Any> = HashMap()
        return Jwts.builder()
            .claims(claims)
            .subject(username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .signWith(SignatureAlgorithm.HS256, secret).compact()
    }

    fun extractUsername(token: String): String {
        return extractAllClaims(token).subject
    }

    private fun extractAllClaims(token: String): Claims {
//        return Jwts.parser().verifyWith() setSigningKey(secret).parseClaimsJws(token).body
    }

    fun validateToken(token: String, username: String): Boolean {
        val extractedUsername = extractUsername(token)
        return (extractedUsername == username && !isTokenExpired(token))
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractAllClaims(token).expiration.before(Date())
    }
}