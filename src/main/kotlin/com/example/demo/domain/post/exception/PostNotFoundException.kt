package com.example.demo.domain.post.exception

import com.example.demo.global.exception.NotFoundException

class PostNotFoundException(
    message: String = "게시글 조회 실패",
) : NotFoundException(message) {
}