package com.example.demo.domain.auth.jwt

import com.example.demo.domain.auth.exception.TokenExpiredException
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts

class JwtAuthenticationFilter(
    secretKey: String,
    expiration: Long
): JwtUtil(secretKey, expiration) {
    fun userEmailFromToken(token: String): String {
        val claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody()

        return claims.subject
    }

    fun validateToken(token: String) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
        } catch (e: ExpiredJwtException) {
            throw TokenExpiredException()
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException(e.message)
        } catch (e: JwtException) {
            throw IllegalArgumentException(e.message)
        }
    }
}