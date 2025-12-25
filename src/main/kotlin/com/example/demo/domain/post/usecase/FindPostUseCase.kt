package com.example.demo.domain.post.usecase

import com.example.demo.domain.post.domain.Post
import com.example.demo.domain.post.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class FindPostUseCase(
    private val postRepository: PostRepository,
) {
    // 게시글 전체 조회
    fun findAllPosts(): List<Post> {
        return postRepository.findAll()
    }
}