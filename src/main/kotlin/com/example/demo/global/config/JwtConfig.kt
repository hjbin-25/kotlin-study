package com.example.demo.global.config

import com.example.demo.domain.auth.jwt.JwtTokenProvider
import com.example.demo.domain.auth.jwt.JwtUtil
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "jwt")
class JwtConfig {

    lateinit var secret: String
    var expiration: Long = 0L

    @Bean
    fun jwtUtil(): JwtUtil {
        return JwtUtil(secret, expiration)
    }

    @Bean
    fun jwtTokenProvider(): JwtTokenProvider {
        return JwtTokenProvider(secret, expiration)
    }
}
