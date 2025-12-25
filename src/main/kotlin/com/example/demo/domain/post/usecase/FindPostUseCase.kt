package com.example.demo.domain.post.usecase

import com.example.demo.domain.post.domain.Post
import com.example.demo.domain.post.exception.PostNotFoundException
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

    // 특정 게시글 조회
    fun findPostById(id: Long?): Post {
        val post = postRepository.findPostByPostId(id)

        if (post.equals(null))
            throw PostNotFoundException()

        return post
    }
}