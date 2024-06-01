package com.example.domain.usecase.auth

import com.example.domain.repository.AuthRepository
import com.example.shared.extensions.notNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import mockAuthEntity
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub

class AuthDataUseCaseTest {
    private val repository = mock<AuthRepository>()
    private val useCase by lazy {
        AuthDataUseCase(repository)
    }

    @Test
    fun `verify authentication with success status`() = runTest {
        repository.stub {
            onBlocking {
                auth(
                    email = FAKE_EMAIL,
                    password = FAKE_PASSWORD
                )
            } doReturn flowOf(Result.success(SUCCESS_MESSAGE_AUTHENTICATION))
        }
        useCase.execute(mockAuthEntity()).collect {
            assert(it.getOrNull() == SUCCESS_MESSAGE_AUTHENTICATION)
        }
    }

    @Test
    fun `verify authentication with error status`() = runTest {
        repository.stub {
            onBlocking {
                auth(
                    email = FAKE_EMAIL,
                    password = FAKE_PASSWORD
                )
            } doReturn flowOf(Result.failure(Exception(ERROR_MESSAGE_AUTHENTICATION)))
        }
        useCase.execute(mockAuthEntity()).collect {
            val messageError = it.getOrElse { it.message.notNull() }
            assert(messageError == ERROR_MESSAGE_AUTHENTICATION)
        }
    }

    private companion object {
        const val FAKE_EMAIL = "edupalacios@hotmail.com"
        const val FAKE_PASSWORD = "Android231."
        const val SUCCESS_MESSAGE_AUTHENTICATION = "User authenticated successfully"
        const val ERROR_MESSAGE_AUTHENTICATION = "Bad credentials."
    }
}
