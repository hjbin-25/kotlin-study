package com.example.demo.domain.post.dto.response

data class FindPostShallow(
    val postId: Long?,
    val postTitle: String,
    val writerId: Long?,
)
