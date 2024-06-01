package com.example.storidemoapp.login

import androidx.activity.ComponentActivity
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.shared.commonResult.ResponseType
import com.example.storidemoapp.ui.login.LoginScreenContent
import com.example.storidemoapp.ui.login.LoginScreenState
import org.junit.Rule
import org.junit.Test
import com.example.storidemoapp.R

class LoginScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loginScreen_DisplayCorrectly() {
        composeTestRule.setContent {
            LoginScreenContent(
                state = LoginScreenState(),
                scrollState = rememberScrollState(),
                emailChanged = {},
                passwordChanged = {},
                updateShowPassword = {},
                updateResponseTypeToNone = {},
                executeLogin = {},
                goToSignup = {},
                onSuccessLogin = {},
            )
        }

        composeTestRule.apply {
            onNodeWithContentDescription(activity.getString(R.string.image_logo_login)).assertExists()
            onNodeWithText(activity.getString(R.string.enter_your_email)).assertExists()
            onNodeWithText(activity.getString(R.string.enter_your_password)).assertExists()
            onNodeWithText(activity.getString(R.string.sign_in)).assertExists()
            onNodeWithText(activity.getString(R.string.not_have_an_Account)).assertExists()
            onNodeWithText(activity.getString(R.string.sign_up)).assertExists()
        }
    }

    @Test
    fun loginScreen_InvalidEmailAndPassword() {
        composeTestRule.setContent {
            LoginScreenContent(
                state = LoginScreenState(
                    isNotValidEmail = true,
                    isNotValidPassword = true
                ),
                scrollState = rememberScrollState(),
                emailChanged = {},
                passwordChanged = {},
                updateShowPassword = {},
                updateResponseTypeToNone = {},
                executeLogin = {},
                goToSignup = {},
                onSuccessLogin = {},
            )
        }

        composeTestRule.apply {
            onNodeWithText(activity.getString(R.string.email_error)).assertIsDisplayed()
            onNodeWithText(activity.getString(R.string.password_error)).assertIsDisplayed()
        }
    }

    @Test
    fun loginScreen_InvalidEmail() {
        composeTestRule.setContent {
            LoginScreenContent(
                state = LoginScreenState(
                    isNotValidEmail = true,
                ),
                scrollState = rememberScrollState(),
                emailChanged = {},
                passwordChanged = {},
                updateShowPassword = {},
                updateResponseTypeToNone = {},
                executeLogin = {},
                goToSignup = {},
                onSuccessLogin = {},
            )
        }

        composeTestRule.apply {
            onNodeWithText(activity.getString(R.string.email_error)).assertIsDisplayed()
        }
    }

    @Test
    fun loginScreen_InvalidPassword() {
        composeTestRule.setContent {
            LoginScreenContent(
                state = LoginScreenState(
                    isNotValidPassword = true
                ),
                scrollState = rememberScrollState(),
                emailChanged = {},
                passwordChanged = {},
                updateShowPassword = {},
                updateResponseTypeToNone = {},
                executeLogin = {},
                goToSignup = {},
                onSuccessLogin = {},
            )
        }

        composeTestRule.apply {
            onNodeWithText(activity.getString(R.string.password_error)).assertIsDisplayed()
        }
    }

    @Test
    fun loginScreen_ShowLoading() {
        composeTestRule.setContent {
            LoginScreenContent(
                state = LoginScreenState(
                    responseType = ResponseType.LOADING
                ),
                scrollState = rememberScrollState(),
                emailChanged = {},
                passwordChanged = {},
                updateShowPassword = {},
                updateResponseTypeToNone = {},
                executeLogin = {},
                goToSignup = {},
                onSuccessLogin = {},
            )
        }

        composeTestRule.apply {
            onNodeWithTag(activity.getString(R.string.stori_loader)).assertIsDisplayed()
        }
    }

    @Test
    fun loginScreen_ShowErrorDialog() {
        composeTestRule.setContent {
            LoginScreenContent(
                state = LoginScreenState(
                    responseType = ResponseType.ERROR
                ),
                scrollState = rememberScrollState(),
                emailChanged = {},
                passwordChanged = {},
                updateShowPassword = {},
                updateResponseTypeToNone = {},
                executeLogin = {},
                goToSignup = {},
                onSuccessLogin = {},
            )
        }

        composeTestRule.apply {
            onNodeWithText(activity.getString(R.string.error_dialog)).assertExists()
        }
    }
}
