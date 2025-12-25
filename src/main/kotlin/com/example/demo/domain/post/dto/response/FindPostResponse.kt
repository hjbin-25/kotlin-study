package com.example.demo.domain.post.dto.response

data class FindPostResponse(
    val postId: Long?,
    val postTitle: String,
    val postText: String,
    val writerId: Long?,
)
