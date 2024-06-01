package com.example.data.repository

import com.example.data.datasource.auth.AuthDataSource
import com.example.data.repository.mocks.mockUser
import com.example.data.repository.mocks.mockUserEntity
import com.example.shared.extensions.notNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub

class AuthDataRepositoryTest {
    private val client = mock<AuthDataSource>()
    private val repository by lazy {
        AuthDataRepository(client, Dispatchers.IO)
    }

    @Test
    fun `verify authentication with success status`() = runTest {
        client.stub {
            onBlocking {
                auth(
                    email = FAKE_EMAIL,
                    password = FAKE_PASSWORD
                )
            } doReturn flowOf(Result.success(SUCCESS_MESSAGE_AUTHENTICATION))
        }

        repository.auth(email = FAKE_EMAIL, password = FAKE_PASSWORD).collect {
            assert(it.getOrNull() == SUCCESS_MESSAGE_AUTHENTICATION)
        }
    }

    @Test
    fun `verify authentication with error status`() = runTest {
        client.stub {
            onBlocking {
                auth(
                    email = FAKE_EMAIL,
                    password = FAKE_PASSWORD
                )
            } doReturn flowOf(Result.failure(Exception(ERROR_MESSAGE_AUTHENTICATION)))
        }

        repository.auth(email = FAKE_EMAIL, password = FAKE_PASSWORD).collect {
            val messageError = it.getOrElse { it.message.notNull() }
            assert(messageError == ERROR_MESSAGE_AUTHENTICATION)
        }
    }

    @Test
    fun `verify user registration with success status`() = runTest {
        client.stub {
            onBlocking {
                register(user = mockUser())
            } doReturn flowOf(Result.success(SUCCESS_MESSAGE_REGISTRATION))
        }

        repository.register(user = mockUserEntity()).collect {
            assert(it.getOrNull() == SUCCESS_MESSAGE_REGISTRATION)
        }
    }

    @Test
    fun `verify user registration with error status`() = runTest {
        client.stub {
            onBlocking {
                register(user = mockUser())
            } doReturn flowOf(Result.failure(Exception(ERROR_MESSAGE_REGISTRATION)))
        }

        repository.register(user = mockUserEntity()).collect {
            val messageError = it.getOrElse { it.message.notNull() }
            assert(messageError == ERROR_MESSAGE_REGISTRATION)
        }
    }


    private companion object {
        const val FAKE_EMAIL = "edupalacios@hotmail.com"
        const val FAKE_PASSWORD = "Android231."
        const val SUCCESS_MESSAGE_AUTHENTICATION = "User authenticated successfully"
        const val ERROR_MESSAGE_AUTHENTICATION = "Bad credentials."
        const val SUCCESS_MESSAGE_REGISTRATION = "User registered successfully"
        const val ERROR_MESSAGE_REGISTRATION = "The user already exist"
    }
}
