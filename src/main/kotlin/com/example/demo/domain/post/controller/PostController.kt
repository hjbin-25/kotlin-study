package com.example.demo.domain.post.controller

import com.example.demo.domain.post.domain.Post
import com.example.demo.domain.post.dto.request.CreatePostRequest
import com.example.demo.domain.post.dto.response.FindPostShallow
import com.example.demo.domain.post.dto.response.FindPostsShallow
import com.example.demo.domain.post.mapper.FindPostShallowDtoMapper
import com.example.demo.domain.post.usecase.CreatePostUseCase
import com.example.demo.domain.post.usecase.FindPostUseCase
import com.example.demo.global.dto.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/post")
class PostController(
    val createPostUseCase: CreatePostUseCase,
    val findPostUseCase: FindPostUseCase
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

    @GetMapping("/find/all/posts")
    fun findAllPosts(): ResponseEntity<ApiResponse<FindPostsShallow>> {
        val posts = findPostUseCase.findAllPosts()
        return ResponseEntity.ok(ApiResponse.success(
            data = FindPostsShallow(
                posts = posts.map { FindPostShallowDtoMapper.toDto(it) }
            )
        ))
    }
}