package com.example.domain.usecase.register

import com.example.domain.entities.UserEntity
import com.example.domain.repository.AuthRepository
import com.example.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterDataUseCase @Inject constructor(private val repository: AuthRepository) :
    UseCase<UserEntity, Result<String>>() {
    override suspend fun buildUseCase(params: UserEntity): Flow<Result<String>> =
        repository.register(params)
}
