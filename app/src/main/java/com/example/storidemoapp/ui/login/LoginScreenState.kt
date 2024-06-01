package com.example.storidemoapp.ui.login

import com.example.shared.commonResult.ResponseType
import com.example.storidemoapp.ui.component.AlertDialog.AlertDialogType

data class LoginScreenState(
    var email: String = "",
    var password: String = "",
    val isPasswordMaskingEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val isNotValidEmail: Boolean = false,
    val isNotValidPassword: Boolean = false,
    val dialogType: AlertDialogType = AlertDialogType.NONE,
    val messageDialog: String = "",
    var responseType: ResponseType = ResponseType.NONE,
    var responseMessageError: String = ""
)