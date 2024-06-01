package com.example.storidemoapp.register

import com.example.storidemoapp.ui.register.form.RegisterScreenViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RegisterScreenViewModelTest {
    private val viewModel by lazy {
        RegisterScreenViewModel()
    }

    @Test
    fun `verify all inputs are invalid`() = runTest {
        viewModel.also {
            it.changeName(EMPTY)
            it.changeSurname(EMPTY)
            it.changeEmail(EMPTY)
            it.changePassword(EMPTY)
            it.verifyInputs()

            advanceUntilIdle()

            assert(it._state.value.isNotValidName)
            assert(it._state.value.isNotValidSurname)
            assert(it._state.value.isNotValidEmail)
            assert(it._state.value.isNotValidPassword)
            assert(it._state.value.isNotValidEmail)
        }
    }

    @Test
    fun `verify name input is invalid`() = runTest {
        viewModel.also {
            it.changeName(INVALID_NAME)
            it.changeSurname(FAKE_SURNAME)
            it.changeEmail(FAKE_EMAIL)
            it.changePassword(FAKE_PASSWORD)
            it.verifyInputs()

            advanceUntilIdle()

            assert(it._state.value.isNotValidName)
        }
    }

    @Test
    fun `verify surname input is invalid`() = runTest {
        viewModel.also {
            it.changeName(FAKE_NAME)
            it.changeSurname(INVALID_SURNAME)
            it.changeEmail(FAKE_EMAIL)
            it.changePassword(FAKE_PASSWORD)
            it.verifyInputs()

            advanceUntilIdle()

            assert(it._state.value.isNotValidSurname)
        }
    }

    @Test
    fun `verify email input is invalid`() = runTest {
        viewModel.also {
            it.changeName(FAKE_NAME)
            it.changeSurname(FAKE_SURNAME)
            it.changeEmail(INVALID_EMAIL)
            it.changePassword(FAKE_PASSWORD)
            it.verifyInputs()

            advanceUntilIdle()

            assert(it._state.value.isNotValidEmail)
        }
    }

    @Test
    fun `verify password input is invalid`() = runTest {
        viewModel.also {
            it.changeName(FAKE_NAME)
            it.changeSurname(FAKE_SURNAME)
            it.changeEmail(FAKE_EMAIL)
            it.changePassword(INVALID_PASSWORD)
            it.verifyInputs()

            advanceUntilIdle()

            assert(it._state.value.isNotValidPassword)
        }
    }

    @Test
    fun `verify mask password is enabled`() = runTest {
        viewModel.also {
            it.changePasswordMasking(true)

            advanceUntilIdle()

            assert(it._state.value.isPasswordMaskingEnabled)
        }
    }

    @Test
    fun `verify mask password is disabled`() = runTest {
        viewModel.also {
            it.changePasswordMasking(false)

            advanceUntilIdle()

            assert(it._state.value.isPasswordMaskingEnabled.not())
        }
    }

    private companion object {
        const val INVALID_NAME = "Pepe2"
        const val INVALID_SURNAME = "Perez2"
        const val INVALID_EMAIL = "edpalacios@hotmail"
        const val INVALID_PASSWORD = "invalid"
        const val FAKE_NAME = "Alberto"
        const val FAKE_SURNAME = "Palacios"
        const val FAKE_EMAIL = "edpalacios@hotmail.com"
        const val FAKE_PASSWORD = "Edu123-"
        const val SUCCESS_MESSAGE_AUTHENTICATION = "User authenticated successfully"
        const val ERROR_MESSAGE_AUTHENTICATION = "Bad credentials."
        const val EMPTY = ""
    }
}
