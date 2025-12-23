package com.example.demo.global.dto

import java.time.LocalDateTime

data class ErrorResponse(
    private var code: Int,
    private var message: String,
    private var timestamp: LocalDateTime,
    private var details: Map<String, String>? = null
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
