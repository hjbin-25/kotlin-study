package com.example.demo.domain.post.controller

import com.example.demo.domain.post.dto.request.CreatePostRequest
import com.example.demo.domain.post.usecase.CreatePostUseCase
import com.example.demo.global.dto.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/post")
class PostController(
    val createPostUseCase: CreatePostUseCase
) {
    @PostMapping("/create")
    fun cratePost(
        @RequestBody request: CreatePostRequest
    ): ResponseEntity<ApiResponse<Void>> {
        createPostUseCase.createPost(request)
        return ResponseEntity
            .created(URI.create("/api/post/create"))
            .body(ApiResponse.success())
    }
}