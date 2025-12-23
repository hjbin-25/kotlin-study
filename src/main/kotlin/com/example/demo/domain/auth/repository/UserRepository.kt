package com.example.demo.domain.auth.repository

import com.example.demo.domain.auth.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByUserEmail(userEmail: String): User?
}