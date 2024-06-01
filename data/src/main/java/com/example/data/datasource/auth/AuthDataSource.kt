package com.example.data.datasource.auth

import com.example.data.model.request.User
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {
    suspend fun auth(email: String, password: String): Flow<Result<String>>
    suspend fun register(user: User): Flow<Result<String>>
}