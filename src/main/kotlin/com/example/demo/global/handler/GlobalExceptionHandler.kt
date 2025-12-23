package com.example.demo.global.handler

import com.example.demo.global.dto.ErrorResponse
import com.example.demo.global.exception.NoAuthorizeException
import com.example.demo.global.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(e: NotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                ErrorResponse.of(
                    code = HttpStatus.NOT_FOUND.value(),
                    message = e.message ?: "리소스를 찾을 수 없습니다",
                    details = mapOf(
                        "error_name" to e::class.simpleName.orEmpty()
                    )
                )
            )
    }

    @ExceptionHandler(NoAuthorizeException::class)
    fun handleNoAuthorizeException(e: NoAuthorizeException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(
                ErrorResponse.of(
                    code = HttpStatus.UNAUTHORIZED.value(),
                    message = e.message ?: "권한이 없습니다",
                    details = mapOf(
                        "error_name" to e::class.simpleName.orEmpty()
                    )
                )
            )
    }
}
