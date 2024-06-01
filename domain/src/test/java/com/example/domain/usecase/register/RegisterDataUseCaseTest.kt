package com.example.domain.usecase.register

import com.example.domain.repository.AuthRepository
import com.example.shared.extensions.notNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import mockUserEntity
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub

class RegisterDataUseCaseTest {
    private val repository = mock<AuthRepository>()
    private val useCase by lazy {
        RegisterDataUseCase(repository)
    }

    @Test
    fun `verify authentication with success status`() = runTest {
        repository.stub {
            onBlocking { register(user = mockUserEntity()) } doReturn flowOf(
                Result.success(
                    SUCCESS_MESSAGE_REGISTRATION
                )
            )
        }
        useCase.execute(params = mockUserEntity()).collect { result ->
            assert(result.getOrNull() == SUCCESS_MESSAGE_REGISTRATION)
        }
    }

    @Test
    fun `verify authentication with error status`() = runTest {
        repository.stub {
            onBlocking { register(user = mockUserEntity()) } doReturn flowOf(
                Result.failure(
                    Exception(ERROR_MESSAGE_REGISTRATION)
                )
            )
        }
        useCase.execute(params = mockUserEntity()).collect { result ->
            val messageError = result.getOrElse { it.message.notNull() }
            assert(messageError == ERROR_MESSAGE_REGISTRATION)
        }
    }

    private companion object {
        const val SUCCESS_MESSAGE_REGISTRATION = "User registered successfully"
        const val ERROR_MESSAGE_REGISTRATION = "The user already exist"
    }
}
