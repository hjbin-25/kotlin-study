package com.example.demo.global.dto

import java.time.LocalDateTime

data class ErrorResponse(
    val code: Int,
    val message: String,
    val timestamp: LocalDateTime,
    val details: Map<String, String>? = null
) {
    companion object {
        fun of(code: Int, message: String): ErrorResponse {
            return ErrorResponse(code, message, LocalDateTime.now(), null)
        }

        fun of(code: Int, message: String, details: Map<String, String>): ErrorResponse {
            return ErrorResponse(code, message, LocalDateTime.now(), details)
        }
    }
}
