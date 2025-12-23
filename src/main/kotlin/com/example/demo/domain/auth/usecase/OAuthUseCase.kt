package com.example.demo.domain.auth.usecase

import com.example.demo.domain.auth.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class OAuthUseCase(
    private val userRepository: UserRepository,
) {

}