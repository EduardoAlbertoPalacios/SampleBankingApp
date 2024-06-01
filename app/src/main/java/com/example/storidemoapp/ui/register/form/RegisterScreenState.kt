package com.example.storidemoapp.ui.register.form

data class RegisterScreenState(
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val password: String = "",
    val isPasswordMaskingEnabled: Boolean = false,
    val isNotValidName: Boolean = false,
    val isNotValidSurname: Boolean = false,
    val isNotValidEmail: Boolean = false,
    val isNotValidPassword: Boolean = false,
    val isValidAllFields: Boolean = false
)