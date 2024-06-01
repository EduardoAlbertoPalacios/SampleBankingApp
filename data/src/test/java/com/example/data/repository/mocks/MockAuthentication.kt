package com.example.data.repository.mocks

import com.example.data.model.request.User
import com.example.data.repository.mocks.Information.FAKE_EMAIL
import com.example.data.repository.mocks.Information.FAKE_IMAGE
import com.example.data.repository.mocks.Information.FAKE_NAME
import com.example.data.repository.mocks.Information.FAKE_PASSWORD
import com.example.data.repository.mocks.Information.FAKE_SURNAME
import com.example.domain.entities.UserEntity

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

private object Information {
    const val FAKE_NAME = "Eduardo"
    const val FAKE_SURNAME = "Palacios"
    const val FAKE_EMAIL = "edupalacios@hotmail.com"
    const val FAKE_PASSWORD = "Android231."
    const val FAKE_IMAGE = "TestImage.jpg"
}
