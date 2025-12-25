package com.example.demo.domain.auth.controller

import com.example.demo.domain.auth.dto.request.GoogleAuthorizeCodeRequest
import com.example.demo.domain.auth.dto.response.GetUserInfoResponse
import com.example.demo.domain.auth.dto.response.GoogleUserInfoResponse
import com.example.demo.domain.auth.usecase.DefaultUserUseCase
import com.example.demo.domain.auth.usecase.OAuthUseCase
import com.example.demo.global.dto.ApiResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val oAuthUseCase: OAuthUseCase,
    private val defaultUserUseCase: DefaultUserUseCase
) {
    @PostMapping("/google/login")
    fun googleLogin(@Valid @RequestBody request: GoogleAuthorizeCodeRequest): ResponseEntity<ApiResponse<GoogleUserInfoResponse>> {
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

    @GetMapping("/find/current-user")
    fun findCurrentUser(): ResponseEntity<ApiResponse<GetUserInfoResponse>> {
        val user = defaultUserUseCase.findCurrentUser()
        return ResponseEntity.ok(
            ApiResponse(
                success = true,
                data = GetUserInfoResponse(
                    userId = user.userId,
                    userEmail = user.userEmail,
                    userName = user.username,
                    userProvider = user.userProvider
                )
            )
        )
    }
}