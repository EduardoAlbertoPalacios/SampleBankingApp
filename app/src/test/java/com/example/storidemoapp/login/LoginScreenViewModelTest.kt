package com.example.storidemoapp.login

import com.example.domain.entities.AuthEntity
import com.example.domain.usecase.UseCase
import com.example.shared.commonResult.ResponseType
import com.example.storidemoapp.mocks.mockAuth
import com.example.storidemoapp.ui.login.LoginScreenViewModel
import com.example.storidemoapp.utils.CoroutinesTestRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginScreenViewModelTest {
    @get:Rule
    val rule = CoroutinesTestRule()

    private val useCase: UseCase<AuthEntity, Result<String>> = mockk()
    private val viewModel by lazy {
        LoginScreenViewModel(useCase)
    }

    @After
    fun tearDown() = unmockkAll()

    @Test
    fun `verify email input is invalid`() = runTest {
        viewModel.also {
            it.changeEmail(INVALID_EMAIL)
            it.verifyInputs()

            advanceUntilIdle()

            assert(it._state.value.isNotValidEmail)
        }
    }

    @Test
    fun `verify password input is invalid`() = runTest {
        viewModel.also {
            it.changeEmail(FAKE_EMAIL)
            it.changePassword(INVALID_PASSWORD)
            it.verifyInputs()

            advanceUntilIdle()

            assert(it._state.value.isNotValidPassword)
        }
    }

    @Test
    fun `verify email and password input are empty`() = runTest {
        viewModel.also {
            it.changeEmail(EMPTY)
            it.changePassword(EMPTY)
            it.verifyInputs()

            advanceUntilIdle()

            assert(it._state.value.isNotValidEmail)
            assert(it._state.value.isNotValidPassword)
        }
    }

    @Test
    fun `do Login with success status`() = runTest {
        viewModel._state.value.email = FAKE_EMAIL
        viewModel._state.value.password = FAKE_PASSWORD

        coEvery { useCase.execute(mockAuth()) } coAnswers {
            flowOf(
                Result.success(
                    SUCCESS_MESSAGE_AUTHENTICATION
                )
            )
        }
        viewModel.also {
            it.doLogin()

            advanceUntilIdle()

            assert(it._state.value.responseType == ResponseType.SUCCESS)
        }

        coVerify {
            useCase.execute(mockAuth())
        }
    }

    @Test
    fun `do Login with error status`() = runTest {
        viewModel._state.value.email = FAKE_EMAIL
        viewModel._state.value.password = FAKE_PASSWORD

        coEvery { useCase.execute(mockAuth()) } coAnswers {
            flowOf(
                Result.failure(
                    Exception(ERROR_MESSAGE_AUTHENTICATION)
                )
            )
        }
        viewModel.also {
            it.doLogin()

            advanceUntilIdle()

            assert(it._state.value.responseType == ResponseType.ERROR)
        }

        coVerify {
            useCase.execute(mockAuth())
        }
    }

    companion object {
        const val INVALID_EMAIL = "edpalacios@hotmail"
        const val INVALID_PASSWORD = "invalid"
        const val FAKE_EMAIL = "edpalacios@hotmail.com"
        const val FAKE_PASSWORD = "Edu123-"
        const val SUCCESS_MESSAGE_AUTHENTICATION = "User authenticated successfully"
        const val ERROR_MESSAGE_AUTHENTICATION = "Bad credentials."
        const val EMPTY = ""
    }
}