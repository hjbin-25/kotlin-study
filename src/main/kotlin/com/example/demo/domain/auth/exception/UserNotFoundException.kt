package com.example.demo.domain.auth.exception

import com.example.demo.global.exception.NotFoundException

class UserNotFoundException(
    message: String = "유저 조회 실패"
): NotFoundException(message)