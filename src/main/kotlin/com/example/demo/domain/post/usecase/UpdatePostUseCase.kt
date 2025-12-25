package com.example.demo.domain.post.usecase

import com.example.demo.domain.auth.usecase.DefaultUserUseCase
import com.example.demo.domain.post.dto.request.UpdatePostRequest
import com.example.demo.domain.post.repository.PostRepository
import com.example.demo.global.exception.NoAuthorizeException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UpdatePostUseCase(
    val postRepository: PostRepository,
    val defaultUserUseCase: DefaultUserUseCase
) {
    // 게시글 수정
    @Transactional
    fun updatePost(request: UpdatePostRequest) {
        val user = defaultUserUseCase.findCurrentUser()
        val post = postRepository.findPostByPostId(request.postId)

        if (user.userId?.equals(post.writer.userId) == false)
            throw NoAuthorizeException()

        post.update(request.postTitle, request.postText)

        postRepository.save(post)
    }
}