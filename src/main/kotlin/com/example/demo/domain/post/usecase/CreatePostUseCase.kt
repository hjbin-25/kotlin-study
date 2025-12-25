package com.example.demo.domain.post.usecase

import com.example.demo.domain.auth.usecase.DefaultUserUseCase
import com.example.demo.domain.post.domain.Post
import com.example.demo.domain.post.dto.request.CreatePostRequest
import com.example.demo.domain.post.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class CreatePostUseCase(
    val repository: PostRepository,
    val defaultUserUseCase: DefaultUserUseCase
) {
    // 게시글 생성
    fun createPost(request: CreatePostRequest) {
        val user = defaultUserUseCase.findCurrentUser()

        val post = Post(
            postTitle = request.postTitle,
            postText = request.postText,
            writer = user
        )

        repository.save(post)
    }
}