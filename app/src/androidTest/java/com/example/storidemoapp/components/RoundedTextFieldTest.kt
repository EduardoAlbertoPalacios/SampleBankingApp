package com.example.storidemoapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.storidemoapp.ui.component.CustomTextfield.RoundedTextField
import com.example.storidemoapp.ui.component.CustomTextfield.RoundedTextFieldModel
import org.junit.Rule
import org.junit.Test

class RoundedTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun roundedButton_DisplayTextCorrectly() {
        composeTestRule.setContent {
            var name = "Eduardo Alberto"
            RoundedTextField(
                model = RoundedTextFieldModel(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default,
                    placeHolderText = "",
                    )
            )
        }

        composeTestRule.apply {
            onNodeWithText("Eduardo Alberto").assertExists()
        }
    }
}