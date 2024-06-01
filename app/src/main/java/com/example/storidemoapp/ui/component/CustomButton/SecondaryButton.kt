package com.example.storidemoapp.ui.component.CustomButton

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.storidemoapp.ui.theme.caption
import com.example.storidemoapp.ui.theme.spacing

@Composable
fun SecondaryButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
) {

    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(MaterialTheme.spacing.dimen16),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),

        ) {
        Text(style = caption, text = text)
    }
}