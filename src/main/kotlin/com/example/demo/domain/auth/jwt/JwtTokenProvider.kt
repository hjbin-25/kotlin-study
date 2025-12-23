package com.example.demo.domain.auth.jwt

import com.example.demo.domain.auth.domain.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.Date

class JwtTokenProvider(
    secretKey: String,
    expiration: Long
): JwtUtil(secretKey, expiration) {

    fun generateToken(user: User): String {
        val now = Date()
        val expiryDate = Date(now.time + expiration)

        return Jwts.builder()
            .setSubject(user.userEmail)
            .claim("name", user.username)
            .claim("provider", user.userProvider.name)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }
}