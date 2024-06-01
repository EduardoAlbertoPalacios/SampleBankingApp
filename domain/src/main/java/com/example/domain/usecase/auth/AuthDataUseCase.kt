package com.example.domain.usecase.auth

import com.example.domain.entities.AuthEntity
import com.example.domain.repository.AuthRepository
import com.example.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthDataUseCase @Inject constructor(private val repository: AuthRepository) :
    UseCase<AuthEntity, Result<String>>() {
    override suspend fun buildUseCase(params: AuthEntity): Flow<Result<String>> =
        repository.auth(params.email, params.password)
}
