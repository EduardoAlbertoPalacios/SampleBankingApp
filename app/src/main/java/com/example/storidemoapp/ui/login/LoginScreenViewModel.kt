package com.example.storidemoapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.AuthEntity
import com.example.domain.usecase.UseCase
import com.example.shared.commonResult.ResponseType
import com.example.shared.extensions.isNotValidEmail
import com.example.shared.extensions.isNotValidPassword
import com.example.shared.extensions.notNull
import com.example.storidemoapp.ui.component.AlertDialog.AlertDialogType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(private val useCase: UseCase<AuthEntity, Result<String>>) :
    ViewModel() {
    @VisibleForTesting
    var _state = MutableStateFlow(LoginScreenState())
    var state = _state.asStateFlow()

    fun verifyInputs() = _state.value.run {
        when {
            email.isBlank() && password.isBlank() -> updateUIStateInputs(
                isNotValidEmail = true,
                isNotValidPassword = true,
            )

            email.isNotValidEmail() -> updateUIStateInputs(isNotValidEmail = true)

            password.isNotValidPassword() -> updateUIStateInputs(isNotValidPassword = true)

            else -> doLogin()
        }
    }

    @VisibleForTesting
    fun doLogin() = viewModelScope.launch {
        useCase.execute(AuthEntity(_state.value.email, _state.value.password))
            .onStart { updateUIState(ResponseType.LOADING) }
            .collect { result ->
                if (result.isSuccess) {
                    updateUIState(ResponseType.SUCCESS)
                } else {
                    _state.update {
                        it.copy(
                            responseType = ResponseType.ERROR,
                            responseMessageError = result.getOrElse { it.message.notNull() })
                    }
                }
            }
    }

    fun changeEmail(input: String) = _state.update {
        it.copy(email = input)
    }

    fun changePassword(input: String) = _state.update {
        it.copy(password = input)
    }

    fun changeResponseTypeToNone() = _state.update {
        it.copy(responseType = ResponseType.NONE)
    }

    fun changePasswordMasking(isVisible: Boolean) = _state.update {
        it.copy(isPasswordMaskingEnabled = isVisible)
    }

    private fun updateUIState(responseType: ResponseType = ResponseType.NONE) = _state.update {
        it.copy(responseType = responseType)
    }

    private fun updateUIStateInputs(
        isLoading: Boolean = false,
        isNotValidEmail: Boolean = false,
        isNotValidPassword: Boolean = false,
        dialogType: AlertDialogType = AlertDialogType.NONE,
        messageDialog: String = "",
    ) = _state.update {
        it.copy(
            isLoading = isLoading,
            isNotValidEmail = isNotValidEmail,
            isNotValidPassword = isNotValidPassword,
            dialogType = dialogType,
            messageDialog = messageDialog,
        )
    }
}
