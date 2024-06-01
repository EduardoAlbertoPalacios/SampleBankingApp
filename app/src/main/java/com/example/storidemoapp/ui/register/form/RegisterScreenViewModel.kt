package com.example.storidemoapp.ui.register.form

import androidx.lifecycle.ViewModel
import com.example.shared.extensions.isNotValidEmail
import com.example.shared.extensions.isNotValidName
import com.example.shared.extensions.isNotValidPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor() : ViewModel() {
    @VisibleForTesting
    var _state = MutableStateFlow(RegisterScreenState())
    var state = _state.asStateFlow()

    fun verifyInputs() = _state.value.run {
        when {
            name.isBlank() && surname.isBlank() && email.isBlank() && password.isBlank() ->
                updateState(
                    isNotValidName = true,
                    isNotValidSurname = true,
                    isNotValidEmail = true,
                    isNotValidPassword = true
                )

            name.isNotValidName() -> updateState(isNotValidName = true)

            surname.isNotValidName() -> updateState(isNotValidSurname = true)

            email.isNotValidEmail() -> updateState(isNotValidEmail = true)

            password.isNotValidPassword() -> updateState(isNotValidPassword = true)

            else -> updateState(isValidAllFields = true)
        }
    }

    fun changeName(input: String) = _state.update {
        it.copy(name = input)
    }

    fun changeSurname(input: String) = _state.update {
        it.copy(surname = input)
    }

    fun changeEmail(input: String) = _state.update {
        it.copy(email = input)
    }

    fun changePassword(input: String) = _state.update {
        it.copy(password = input)
    }

    fun changePasswordMasking(isVisible: Boolean) = _state.update {
        it.copy(isPasswordMaskingEnabled = isVisible)
    }

    private fun updateState(
        isNotValidName: Boolean = false,
        isNotValidSurname: Boolean = false,
        isNotValidEmail: Boolean = false,
        isNotValidPassword: Boolean = false,
        isValidAllFields: Boolean = false
    ) = _state.update {
        it.copy(
            isNotValidName = isNotValidName,
            isNotValidSurname = isNotValidSurname,
            isNotValidEmail = isNotValidEmail,
            isNotValidPassword = isNotValidPassword,
            isValidAllFields = isValidAllFields
        )
    }
}