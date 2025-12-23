package com.example.demo.global.dto

data class ApiResponse<T>(
    val success: Boolean,
    val data: T? = null,
    val message: String? = null,
    val error: ErrorResponse? = null
) {
    companion object {
        fun success(): ApiResponse<Void> =
            ApiResponse(
                success = true,
                data = null,
                message = null,
                error = null
            )

        fun <T> success(data: T): ApiResponse<T> =
            ApiResponse(
                success = true,
                data = data,
                message = null,
                error = null
            )

        fun <T> success(data: T, message: String): ApiResponse<T> =
            ApiResponse(
                success = true,
                data = null,
                message = message,
                error = null
            )

        fun error(error: ErrorResponse): ApiResponse<Void> =
            ApiResponse(
                success = false,
                data = null,
                message = null,
                error = error
            )

        fun error(error: ErrorResponse, message: String): ApiResponse<Void> =
            ApiResponse(
                success = false,
                data = null,
                message = message,
                error = error
            )
    }
}
