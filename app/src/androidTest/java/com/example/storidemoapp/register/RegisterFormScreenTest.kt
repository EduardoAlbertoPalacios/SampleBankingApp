package com.example.storidemoapp.register

import androidx.activity.ComponentActivity
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.storidemoapp.R
import com.example.storidemoapp.ui.register.form.RegisterFormScreenContent
import com.example.storidemoapp.ui.register.form.RegisterScreenState
import org.junit.Rule
import org.junit.Test

class RegisterFormScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loginScreen_DisplayCorrectly() {
        composeTestRule.setContent {
            RegisterFormScreenContent(
                state = RegisterScreenState(),
                scrollState = rememberScrollState(),
                nameChanged = {},
                surnameChanged = {},
                emailChanged = {},
                passwordChanged = {},
                updateShowPassword = {},
                backToLoginScreen = {},
                verifyInputs = {},
                goToNextScreen = {},
            )
        }

        composeTestRule.apply {
            onNodeWithText(activity.getString(R.string.register_title)).assertExists()
            onNodeWithText(activity.getString(R.string.register_description)).assertExists()
            onNodeWithText(activity.getString(R.string.enter_your_name)).assertExists()
            onNodeWithText(activity.getString(R.string.enter_your_surname)).assertExists()
            onNodeWithText(activity.getString(R.string.enter_your_email)).assertExists()
            onNodeWithText(activity.getString(R.string.enter_your_password)).assertExists()
            onNodeWithText(activity.getString(R.string.btn_continue)).assertExists()
        }
    }

    @Test
    fun registerFormScreen_InvalidAllFields() {
        composeTestRule.setContent {
            RegisterFormScreenContent(
                state = RegisterScreenState(
                    isNotValidName = true,
                    isNotValidSurname = true,
                    isNotValidEmail = true,
                    isNotValidPassword = true
                ),
                scrollState = rememberScrollState(),
                nameChanged = {},
                surnameChanged = {},
                emailChanged = {},
                passwordChanged = {},
                updateShowPassword = {},
                backToLoginScreen = {},
                verifyInputs = {},
                goToNextScreen = {},
            )
        }

        composeTestRule.apply {
            onNodeWithText(activity.getString(R.string.name_error)).assertIsDisplayed()
            onNodeWithText(activity.getString(R.string.surname_error)).assertIsDisplayed()
            onNodeWithText(activity.getString(R.string.email_error)).assertIsDisplayed()
            onNodeWithText(activity.getString(R.string.password_error)).assertIsDisplayed()
        }
    }

    @Test
    fun registerFormScreen_InvalidName() {
        composeTestRule.setContent {
            RegisterFormScreenContent(
                state = RegisterScreenState(
                    isNotValidName = true,
                ),
                scrollState = rememberScrollState(),
                nameChanged = {},
                surnameChanged = {},
                emailChanged = {},
                passwordChanged = {},
                updateShowPassword = {},
                backToLoginScreen = {},
                verifyInputs = {},
                goToNextScreen = {},
            )
        }

        composeTestRule.apply {
            onNodeWithText(activity.getString(R.string.name_error)).assertIsDisplayed()
        }
    }

    @Test
    fun registerFormScreen_InvalidSurname() {
        composeTestRule.setContent {
            RegisterFormScreenContent(
                state = RegisterScreenState(
                    isNotValidSurname = true,
                ),
                scrollState = rememberScrollState(),
                nameChanged = {},
                surnameChanged = {},
                emailChanged = {},
                passwordChanged = {},
                updateShowPassword = {},
                backToLoginScreen = {},
                verifyInputs = {},
                goToNextScreen = {},
            )
        }

        composeTestRule.apply {
            onNodeWithText(activity.getString(R.string.surname_error)).assertIsDisplayed()
        }
    }

    @Test
    fun registerFormScreen_InvalidEmail() {
        composeTestRule.setContent {
            RegisterFormScreenContent(
                state = RegisterScreenState(
                    isNotValidEmail = true,
                ),
                scrollState = rememberScrollState(),
                nameChanged = {},
                surnameChanged = {},
                emailChanged = {},
                passwordChanged = {},
                updateShowPassword = {},
                backToLoginScreen = {},
                verifyInputs = {},
                goToNextScreen = {},
            )
        }

        composeTestRule.apply {
            onNodeWithText(activity.getString(R.string.email_error)).assertIsDisplayed()
        }
    }

    @Test
    fun registerFormScreen_InvalidPassword() {
        composeTestRule.setContent {
            RegisterFormScreenContent(
                state = RegisterScreenState(
                    isNotValidPassword = true,
                ),
                scrollState = rememberScrollState(),
                nameChanged = {},
                surnameChanged = {},
                emailChanged = {},
                passwordChanged = {},
                updateShowPassword = {},
                backToLoginScreen = {},
                verifyInputs = {},
                goToNextScreen = {},
            )
        }

        composeTestRule.apply {
            onNodeWithText(activity.getString(R.string.password_error)).assertIsDisplayed()
        }
    }
}
