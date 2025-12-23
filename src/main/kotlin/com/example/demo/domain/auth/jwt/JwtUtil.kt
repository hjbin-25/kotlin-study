package com.example.demo.domain.auth.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import java.security.Key

open class JwtUtil(
    secretKey: String,
    protected val expiration: Long
) {
    protected val key: Key = Keys.hmacShaKeyFor(secretKey.toByteArray())

    fun getUserEmailFromToken(token: String): String {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
            .subject
    }

    fun getCurrentUserEmail(): String? {
        val authentication: Authentication? = SecurityContextHolder.getContext().authentication
        return authentication?.name
    }
}
