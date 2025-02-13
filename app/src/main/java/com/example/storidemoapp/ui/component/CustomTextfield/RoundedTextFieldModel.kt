package com.example.storidemoapp.ui.component.CustomTextfield

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation

data class RoundedTextFieldModel(
    val value: String,
    val onValueChange: (String) -> Unit,
    val modifier: Modifier = Modifier,
    val trailingIcon: @Composable (() -> Unit)? = null,
    val placeHolderText: String,
    val backgroundColor: Color = Color.Transparent,
    val textColor: Color = Color.Black,
    val keyboardOptions: KeyboardOptions,
    val keyboardActions: KeyboardActions,
    val visualTransformation: VisualTransformation = VisualTransformation.None,
    val isError: Boolean = false,
    val supportingText: @Composable (() -> Unit)? = null,
)