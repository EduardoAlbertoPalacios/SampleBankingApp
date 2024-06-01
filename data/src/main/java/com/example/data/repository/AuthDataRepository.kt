package com.example.data.repository

import com.example.data.datasource.auth.AuthDataSource
import com.example.data.model.request.User
import com.example.domain.entities.UserEntity
import com.example.domain.repository.AuthRepository
import com.example.shared.onError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthDataRepository @Inject constructor(
    private val client: AuthDataSource,
    private val coroutineDispatcher: CoroutineDispatcher
) : AuthRepository {

    override suspend fun auth(email:String, password: String): Flow<Result<String>> {
        return client.auth(email, password)
            .onError()
            .flowOn(coroutineDispatcher)
    }

    override suspend fun register(user: UserEntity): Flow<Result<String>> {
        return user.run {
            client.register(User(name, surname, email, password, imageUrl))
                .onError()
                .flowOn(coroutineDispatcher)
        }
    }
}