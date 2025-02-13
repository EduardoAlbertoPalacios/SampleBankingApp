package com.example.storidemoapp.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.shared.commonResult.ResponseType
import com.example.storidemoapp.R
import com.example.storidemoapp.ui.component.AlertDialog.AlertDialogModel
import com.example.storidemoapp.ui.component.AlertDialog.AlertDialogType
import com.example.storidemoapp.ui.component.AlertDialog.ShowAlertDialog
import com.example.storidemoapp.ui.component.CustomButton.PrimaryButton
import com.example.storidemoapp.ui.component.CustomTextfield.RoundedTextField
import com.example.storidemoapp.ui.component.CustomTextfield.RoundedTextFieldModel
import com.example.storidemoapp.ui.component.ProgressBar.StoriProgressBar
import com.example.storidemoapp.ui.theme.DemoAppTheme
import com.example.storidemoapp.ui.theme.spacing
import com.example.storidemoapp.ui.theme.subtitle1

@Composable
fun LoginScreen(
    viewModelLogin: LoginScreenViewModel = hiltViewModel(),
    onSuccessLogin: () -> Unit,
    goToSignup: () -> Unit
) =
    viewModelLogin.apply {
        val state by state.observeAsState()
        state?.let {
            LoginScreenContent(
                goToSignup = goToSignup,
                state = it,
                scrollState = rememberScrollState(),
                emailChanged = this::changeEmail,
                passwordChanged = this::changePassword,
                updateShowPassword = this::changePasswordMasking,
                updateResponseTypeToNone = this::changeResponseTypeToNone,
                executeLogin = this::verifyInputs,
                onSuccessLogin = onSuccessLogin
            )
        }
    }

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreenContent(
    state: LoginScreenState,
    scrollState: ScrollState,
    emailChanged: (String) -> Unit,
    passwordChanged: (String) -> Unit,
    updateShowPassword: (Boolean) -> Unit,
    updateResponseTypeToNone: () -> Unit,
    executeLogin: () -> Unit,
    goToSignup: () -> Unit,
    onSuccessLogin: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        Column(
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.dimen16,
                    end = MaterialTheme.spacing.dimen16
                )
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.secondary)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_ids),
                contentDescription = stringResource(id = R.string.image_logo_login),
                Modifier
                    .padding(MaterialTheme.spacing.dimen16)
                    .size(MaterialTheme.spacing.dimen150)
            )

            RoundedTextField(
                RoundedTextFieldModel(
                    value = state.email,
                    onValueChange = { newText -> emailChanged(newText) },
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = Color.White,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Email,
                            contentDescription = stringResource(R.string.enter_your_email),
                        )
                    },
                    placeHolderText = stringResource(R.string.enter_your_email),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    visualTransformation = VisualTransformation.None,
                    isError = state.isNotValidEmail,
                    supportingText = {
                        if (state.isNotValidEmail) {
                            Text(
                                modifier = Modifier.padding(start = MaterialTheme.spacing.dimen24),
                                text = stringResource(id = R.string.email_error),
                                color = Color.Red,
                            )
                        }
                    },
                )
            )

            Spacer(modifier = Modifier.size(MaterialTheme.spacing.dimen32))

            RoundedTextField(
                RoundedTextFieldModel(
                    value = state.password,
                    onValueChange = { newText -> passwordChanged(newText) },
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = Color.White,
                    trailingIcon = {
                        IconButton(onClick = { updateShowPassword.invoke(!state.isPasswordMaskingEnabled) }) {
                            Icon(
                                imageVector = if (state.isPasswordMaskingEnabled) {
                                    Icons.Outlined.Lock
                                } else {
                                    Icons.Outlined.Face
                                },
                                contentDescription = stringResource(R.string.enter_your_password)
                            )
                        }
                    },
                    placeHolderText = stringResource(R.string.enter_your_password),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    ),
                    visualTransformation = if (state.isPasswordMaskingEnabled) {
                        PasswordVisualTransformation()
                    } else {
                        VisualTransformation.None
                    },
                    isError = state.isNotValidPassword,
                    supportingText = {
                        if (state.isNotValidPassword) {
                            Text(
                                modifier = Modifier.padding(start = MaterialTheme.spacing.dimen24),
                                text = stringResource(id = R.string.password_error),
                                color = Color.Red,
                            )
                        }
                    }
                )
            )

            Spacer(modifier = Modifier.size(MaterialTheme.spacing.dimen24))

            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MaterialTheme.spacing.dimen16),
                onClick = { executeLogin.invoke() },
                text = stringResource(id = R.string.sign_in),
            )

            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.not_have_an_Account),
                    style = subtitle1.copy(Color.White)
                )

                TextButton(
                    onClick = { goToSignup.invoke() },
                ) {
                    Text(
                        text = stringResource(R.string.sign_up),
                        style = subtitle1.copy(
                            MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }

        }
    }

    when (state.responseType) {
        ResponseType.LOADING -> StoriProgressBar()

        ResponseType.ERROR -> ShowAlertDialog(
            alertDialogModel = AlertDialogModel(
                AlertDialogType.ERROR,
                title = stringResource(id = R.string.error_dialog),
                body = state.responseMessageError,
                actionButton = updateResponseTypeToNone
            )
        )

        ResponseType.SUCCESS -> {
            onSuccessLogin.invoke()
            updateResponseTypeToNone.invoke()
        }

        else -> Unit
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenContentPreview() {
    DemoAppTheme {
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
}