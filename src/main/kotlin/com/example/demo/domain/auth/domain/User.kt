package com.example.demo.domain.auth.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    var userId: Long? = null,
    @Column(name = "u_username", nullable = false)
    var username: String,
    @Column(name = "u_name", nullable = false)
    var userName: String,
    @Column(name = "u_password", nullable = false)
    var userPassword: String
) {
}