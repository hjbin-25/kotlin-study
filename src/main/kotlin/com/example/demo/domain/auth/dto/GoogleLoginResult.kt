package com.example.demo.domain.auth.dto

import com.example.demo.domain.auth.domain.User

data class GoogleLoginResult(
    val user: User,
    val token: String
)
