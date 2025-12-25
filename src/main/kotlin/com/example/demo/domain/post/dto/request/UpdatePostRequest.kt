package com.example.demo.domain.post.dto.request

data class UpdatePostRequest(
    val postId: Long?,
    val postTitle: String,
    val postText: String
)
