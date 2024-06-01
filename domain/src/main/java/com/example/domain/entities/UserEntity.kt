package com.example.domain.entities

data class UserEntity(
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val imageUrl: String
)