package com.example.storidemoapp.ui.register.form

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.storidemoapp.R
import com.example.storidemoapp.navigation.routes.NavigationItem
import com.example.storidemoapp.ui.component.CustomButton.PrimaryButton
import com.example.storidemoapp.ui.component.CustomTextfield.RoundedTextField
import com.example.storidemoapp.ui.component.CustomTextfield.RoundedTextFieldModel
import com.example.storidemoapp.ui.theme.DemoAppTheme
import com.example.storidemoapp.ui.theme.headline6
import com.example.storidemoapp.ui.theme.spacing
import com.example.storidemoapp.ui.theme.subtitle1
import com.google.gson.Gson

@Composable
fun RegisterFormScreen(
    viewModel: RegisterScreenViewModel = hiltViewModel(),
    backToLoginScreen: () -> Unit,
    goToNextScreen: (String) -> Unit,
) {
    viewModel.apply {
        val state by state.collectAsState()
        RegisterFormScreenContent(
            state = state,
            scrollState = rememberScrollState(),
            nameChanged = this::changeName,
            surnameChanged = this::changeSurname,
            emailChanged = this::changeEmail,
            passwordChanged = this::changePassword,
            updateShowPassword = this::changePasswordMasking,
            backToLoginScreen = { backToLoginScreen.invoke() },
            verifyInputs = this::verifyInputs,
            goToNextScreen = goToNextScreen
        )

    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterFormScreenContent(
    state: RegisterScreenState,
    scrollState: ScrollState,
    nameChanged: (String) -> Unit,
    surnameChanged: (String) -> Unit,
    emailChanged: (String) -> Unit,
    passwordChanged: (String) -> Unit,
    updateShowPassword: (Boolean) -> Unit,
    backToLoginScreen: () -> Unit,
    verifyInputs: () -> Unit,
    goToNextScreen: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(MaterialTheme.spacing.dimen24)
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.Start
    ) {

        if (state.isValidAllFields) {
            val registerFormModel = Gson().toJson(
                RegisterFormModel(
                    name = state.name,
                    surname = state.surname,
                    email = state.email,
                    password = state.password
                )
            )
            goToNextScreen.invoke("${NavigationItem.UploadPhoto.route}/$registerFormModel")
        }
        val focusManager = LocalFocusManager.current

        Text(
            modifier = Modifier.padding(top = MaterialTheme.spacing.dimen16),
            text = stringResource(id = R.string.register_title),
            style = headline6.copy(color = MaterialTheme.colorScheme.primary)
        )

        Text(
            modifier = Modifier.padding(top = MaterialTheme.spacing.dimen16),
            text = stringResource(id = R.string.register_description),
            style = subtitle1.copy(color = Color.Gray)
        )

        RoundedTextField(
            RoundedTextFieldModel(
                value = state.name,
                onValueChange = { newText -> nameChanged(newText) },
                modifier = Modifier
                    .padding(top = MaterialTheme.spacing.dimen48)
                    .fillMaxWidth(),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = stringResource(R.string.enter_your_name),
                    )
                },
                placeHolderText = stringResource(R.string.enter_your_name),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                isError = state.isNotValidName,
                supportingText = {
                    if (state.isNotValidName) {
                        Text(
                            modifier = Modifier.padding(start = MaterialTheme.spacing.dimen24),
                            text = stringResource(id = R.string.name_error),
                            color = Color.Red,
                        )
                    }
                },
            )
        )

        RoundedTextField(
            RoundedTextFieldModel(
                value = state.surname,
                onValueChange = { newText -> surnameChanged(newText) },
                modifier = Modifier
                    .padding(top = MaterialTheme.spacing.dimen48)
                    .fillMaxWidth(),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = stringResource(R.string.enter_your_surname),
                    )
                },
                placeHolderText = stringResource(R.string.enter_your_surname),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                isError = state.isNotValidSurname,
                supportingText = {
                    if (state.isNotValidSurname) {
                        Text(
                            modifier = Modifier.padding(start = MaterialTheme.spacing.dimen24),
                            text = stringResource(id = R.string.surname_error),
                            color = Color.Red,
                        )
                    }
                },
            )
        )

        RoundedTextField(
            RoundedTextFieldModel(
                value = state.email,
                onValueChange = { newText -> emailChanged(newText) },
                modifier = Modifier
                    .padding(top = MaterialTheme.spacing.dimen48)
                    .fillMaxWidth(),
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

        RoundedTextField(
            RoundedTextFieldModel(
                value = state.password,
                onValueChange = { newText -> passwordChanged(newText) },
                modifier = Modifier
                    .padding(top = MaterialTheme.spacing.dimen48)
                    .fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { updateShowPassword(!state.isPasswordMaskingEnabled) }) {
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
        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.spacing.dimen48),
            onClick = { verifyInputs.invoke() },
            text = stringResource(id = R.string.btn_continue)
        )

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.already_have_an_account),
                style = subtitle1
            )

            TextButton(
                onClick = { backToLoginScreen.invoke() },
            ) {
                Text(
                    text = stringResource(R.string.login),
                    style = subtitle1.copy(
                        MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterFormScreenContentPreview() {
    DemoAppTheme {
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
            goToNextScreen = {}
        )
    }
}
