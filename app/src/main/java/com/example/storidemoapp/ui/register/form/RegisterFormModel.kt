package com.example.storidemoapp.ui.register.form

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RegisterFormModel(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("surname")
    val surname: String = "",
    @SerializedName("email")
    val email: String = "",
    @SerializedName("password")
    val password: String = ""
) : Serializable
