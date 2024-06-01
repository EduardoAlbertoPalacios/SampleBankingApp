package com.example.storidemoapp.ui.register.picture

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.UserEntity
import com.example.domain.usecase.UseCase
import com.example.shared.commonResult.ResponseType
import com.example.shared.extensions.notNull
import com.example.storidemoapp.ui.register.form.RegisterFormModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

@HiltViewModel
class TakePhotoViewModel @Inject constructor(private val useCase: UseCase<UserEntity, Result<String>>) :
    ViewModel() {
    private var _state = MutableStateFlow(TakePhotoState())
    var state = _state.asStateFlow()
    var captureImageUri by mutableStateOf(Uri.EMPTY)
    var captureImagePath by mutableStateOf("")

    fun verifyInputs(registerFormModel: RegisterFormModel) {
        if (captureImagePath.isNotEmpty()) {
            registerUser(registerFormModel)
        } else {
            _state.update { it.copy(isPhotoUnavailable = true) }
        }
    }

    @VisibleForTesting
     fun registerUser(registerFormModel: RegisterFormModel) = viewModelScope.launch {
        registerFormModel.run {
            useCase.execute(
                UserEntity(
                    name,
                    surname,
                    email,
                    password,
                    captureImagePath
                )
            )
        }.onStart { updateUIState(ResponseType.LOADING) }
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

    private fun updateUIState(responseType: ResponseType = ResponseType.NONE) = _state.update {
        it.copy(responseType = responseType)
    }

    fun updatePhotoUnavailable() = _state.update {
        it.copy(isPhotoUnavailable = false)
    }

    fun updateResponseTypeToNone() = _state.update {
        it.copy(responseType = ResponseType.NONE)
    }

    fun takePhoto(input: Boolean) = _state.update {
        it.copy(isTakePhotoPressed = input)
    }

    fun updateCaptureImagePath(input: String) {
        captureImagePath = input
    }

    fun updateCaptureImageUri(input: Uri) {
        captureImageUri = input
    }
}
