package com.example.demo.domain.auth.dto.response

import com.example.demo.domain.auth.domain.Provider

data class GetUserInfoResponse(
    val userId: Long?,
    val userEmail: String,
    val userName: String,
    val userProvider: Provider
)
