package com.example.demo.domain.post.mapper

import com.example.demo.domain.post.domain.Post
import com.example.demo.domain.post.dto.response.FindPostShallow

object FindPostShallowDtoMapper {
    fun toDto(post: Post): FindPostShallow {
        return FindPostShallow(
            postId = post.postId,
            postTitle = post.postTitle,
            writerId = post.writer.userId
        )
    }
}