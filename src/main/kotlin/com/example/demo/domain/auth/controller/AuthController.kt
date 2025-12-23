package com.example.demo.domain.auth.controller

import com.example.demo.domain.auth.dto.request.GoogleAuthorizeCodeRequest
import com.example.demo.domain.auth.dto.response.GoogleUserInfoResponse
import com.example.demo.domain.auth.usecase.OAuthUseCase
import com.example.demo.global.dto.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val oAuthUseCase: OAuthUseCase
) {
    @PostMapping("/google/login")
    fun googleLogin(@RequestBody request: GoogleAuthorizeCodeRequest): ResponseEntity<ApiResponse<GoogleUserInfoResponse>> {
        val result = oAuthUseCase.googleLogin(request)
        return ResponseEntity.ok(
            ApiResponse(
                success = true,
                data = GoogleUserInfoResponse(
                    id = result.user.userId,
                    email = result.user.userEmail,
                    name = result.user.username,
                    accessToken = result.token,
                    provider = result.user.userProvider
                )
            )
        )
    }
}