package com.example.storidemoapp.ui.component.CustomTextfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.text.TextStyle
import com.example.storidemoapp.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoundedTextField(model: RoundedTextFieldModel) {
    model.apply {
        Column(horizontalAlignment = Alignment.Start) {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = modifier,
                isError = isError,
                colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = backgroundColor),
                textStyle = TextStyle(color = textColor),
                shape = RoundedCornerShape(MaterialTheme.spacing.dimen12),
                trailingIcon = trailingIcon,
                placeholder = { Text(text = placeHolderText) },
                singleLine = true,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                visualTransformation = visualTransformation,
            )
            supportingText?.invoke()
        }
    }
}