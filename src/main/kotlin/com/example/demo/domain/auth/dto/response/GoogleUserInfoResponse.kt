package com.example.demo.domain.auth.dto.response

import com.example.demo.domain.auth.domain.Provider

data class GoogleUserInfoResponse(
    val id: String,
    val email: String,
    val name: String,
    val accessToken: String,
    val provider: Provider
)