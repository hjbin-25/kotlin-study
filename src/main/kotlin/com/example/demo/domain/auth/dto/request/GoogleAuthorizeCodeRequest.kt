package com.example.demo.domain.auth.dto.request

import jakarta.validation.constraints.NotBlank

data class GoogleAuthorizeCodeRequest(
    @field:NotBlank
    val code: String,
    @field:NotBlank
    val redirectUri: String
)