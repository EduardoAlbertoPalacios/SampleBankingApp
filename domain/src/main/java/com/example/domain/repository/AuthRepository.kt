package com.example.domain.repository

import com.example.domain.entities.UserEntity
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun auth(email:String, password: String): Flow<Result<String>>
    suspend fun register(user: UserEntity): Flow<Result<String>>
}