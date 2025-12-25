package com.example.demo.domain.post.domain

import com.example.demo.domain.auth.domain.User
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDateTime

@Entity
@Table(name = "posts")
class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id", nullable = false)
    val postId: Long?,

    @Column(name = "p_title", nullable = false)
    val postTitle: String,

    @Column(name = "p_text", nullable = false)
    val postTest: String,

    @CreationTimestamp
    @JsonIgnore
    @Column(name = "p_created_at", nullable = false)
    val postCreatedAt: LocalDateTime,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_u_id", referencedColumnName = "u_id", updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    val writer: User
)