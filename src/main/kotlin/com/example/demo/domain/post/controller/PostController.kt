package com.example.demo.domain.post.controller

import com.example.demo.domain.post.domain.Post
import com.example.demo.domain.post.dto.request.CreatePostRequest
import com.example.demo.domain.post.dto.request.UpdatePostRequest
import com.example.demo.domain.post.dto.response.FindPostResponse
import com.example.demo.domain.post.dto.response.FindPostShallow
import com.example.demo.domain.post.dto.response.FindPostsShallow
import com.example.demo.domain.post.mapper.FindPostShallowDtoMapper
import com.example.demo.domain.post.usecase.CreatePostUseCase
import com.example.demo.domain.post.usecase.FindPostUseCase
import com.example.demo.domain.post.usecase.UpdatePostUseCase
import com.example.demo.global.dto.ApiResponse
import jakarta.validation.Valid
import lombok.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/post")
class PostController(
    val createPostUseCase: CreatePostUseCase,
    val findPostUseCase: FindPostUseCase,
    val updatePostUseCase: UpdatePostUseCase
) {
    @PostMapping("/create")
    fun cratePost(
        @Valid @RequestBody request: CreatePostRequest
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

    @GetMapping("/find/post/{id}")
    fun findPostById(@PathVariable id: Long?): ResponseEntity<ApiResponse<FindPostResponse>> {
        val post = findPostUseCase.findPostById(id)
        return ResponseEntity.ok(ApiResponse.success(
            data = FindPostResponse(
                postId = post.postId,
                postTitle = post.postTitle,
                postText = post.postText,
                writerId = post.writer.userId
            )
        ))
    }

    @PostMapping("/update/post/{id}")
    fun updatePost(@Valid request: UpdatePostRequest): ResponseEntity<ApiResponse<Void>> {
        updatePostUseCase.updatePost(request)
        return ResponseEntity.ok(ApiResponse.success())
    }
}