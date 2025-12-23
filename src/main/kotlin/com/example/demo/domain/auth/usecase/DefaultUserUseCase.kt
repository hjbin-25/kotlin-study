package com.example.demo.domain.auth.usecase

import com.example.demo.domain.auth.domain.User
import com.example.demo.domain.auth.jwt.JwtUtil
import com.example.demo.domain.auth.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class DefaultUserUseCase(
    val jwtUtil: JwtUtil,
    val userRepository: UserRepository
) {
    fun findCurrentUser(): User {
        val userEmail = jwtUtil.getCurrentUserEmail()
        return userRepository.findByUserEmail(userEmail)
    }
}