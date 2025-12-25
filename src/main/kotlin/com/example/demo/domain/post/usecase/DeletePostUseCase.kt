package com.example.demo.domain.post.usecase

import com.example.demo.domain.auth.usecase.DefaultUserUseCase
import com.example.demo.domain.post.repository.PostRepository
import com.example.demo.global.exception.NoAuthorizeException
import org.springframework.stereotype.Service

@Service
class DeletePostUseCase(
    val postRepository: PostRepository,
    val defaultUserUseCase: DefaultUserUseCase
) {
    // 게시글 제거
    fun deletePost(id: Long?) {
        val user = defaultUserUseCase.findCurrentUser()
        val post = postRepository.findPostByPostId(id)

        if (user.userId?.equals(post.writer.userId) == false)
            throw NoAuthorizeException()

        postRepository.delete(post)
    }
}