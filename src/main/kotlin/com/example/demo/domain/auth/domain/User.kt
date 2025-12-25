package com.example.demo.domain.auth.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    var userId: Long?,
    @Column(name = "u_email", nullable = false, unique = true)
    var userEmail: String,
    @Column(name = "u_username", nullable = false)
    var username: String,
    @Column(name = "u_password", nullable = false)
    var userPassword: String,
    @Column(name = "u_provider", nullable = false)
    @Enumerated(EnumType.STRING)
    var userProvider: Provider,
)