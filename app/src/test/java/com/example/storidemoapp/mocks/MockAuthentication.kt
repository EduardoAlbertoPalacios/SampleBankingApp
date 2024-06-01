package com.example.storidemoapp.mocks

import com.example.data.model.request.User
import com.example.domain.entities.AuthEntity
import com.example.domain.entities.UserEntity
import com.example.storidemoapp.mocks.Information.FAKE_EMAIL
import com.example.storidemoapp.mocks.Information.FAKE_IMAGE
import com.example.storidemoapp.mocks.Information.FAKE_NAME
import com.example.storidemoapp.mocks.Information.FAKE_PASSWORD
import com.example.storidemoapp.mocks.Information.FAKE_SURNAME
import com.example.storidemoapp.ui.register.form.RegisterFormModel

fun mockAuth() = AuthEntity(
    email = FAKE_EMAIL,
    password = FAKE_PASSWORD,
)

fun mockUser() = User(
    name = FAKE_NAME,
    surname = FAKE_SURNAME,
    email = FAKE_EMAIL,
    password = FAKE_PASSWORD,
    imageUrl = FAKE_IMAGE
)

fun mockUserEntity() = UserEntity(
    name = FAKE_NAME,
    surname = FAKE_SURNAME,
    email = FAKE_EMAIL,
    password = FAKE_PASSWORD,
    imageUrl = FAKE_IMAGE
)

fun mockRegisterFormModel() = RegisterFormModel(
    name = FAKE_NAME,
    surname = FAKE_SURNAME,
    email = FAKE_EMAIL,
    password = FAKE_PASSWORD,
)

private object Information {
    const val FAKE_NAME = "Eduardo"
    const val FAKE_SURNAME = "Palacios"
    const val FAKE_EMAIL = "edpalacios@hotmail.com"
    const val FAKE_PASSWORD = "Edu123-"
    const val FAKE_IMAGE = "TestImage.jpg"
}
