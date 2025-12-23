package com.example.demo.domain.auth.dto

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.google")
data class GoogleOAuthProperties(
    val clientId: String,
    val clientSecret: String
)
