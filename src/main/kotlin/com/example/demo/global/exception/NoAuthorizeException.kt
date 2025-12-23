package com.example.demo.global.exception

class NoAuthorizeException(
    message: String = "리소스를 찾을 수 없습니다"
) : RuntimeException(message)