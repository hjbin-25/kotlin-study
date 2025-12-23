package com.example.demo.domain.auth.exception

import com.example.demo.global.exception.NoAuthorizeException

class WrongAccessTokenException(
    message: String = "잘못된 액세스 토큰"
) : NoAuthorizeException(message)