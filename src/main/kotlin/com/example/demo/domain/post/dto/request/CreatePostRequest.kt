package com.example.demo.domain.post.dto.request

import jakarta.validation.constraints.NotBlank

data class CreatePostRequest(
    @field:NotBlank
    val postTitle: String,
    @field:NotBlank
    val postText: String
)
