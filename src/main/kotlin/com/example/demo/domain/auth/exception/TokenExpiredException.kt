package com.example.demo.domain.auth.exception

import com.example.demo.global.exception.NoAuthorizeException

class TokenExpiredException(
    message: String? = "토큰 만료",
): NoAuthorizeException(message)