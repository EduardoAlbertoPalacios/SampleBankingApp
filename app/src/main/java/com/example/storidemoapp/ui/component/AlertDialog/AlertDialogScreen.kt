package com.example.storidemoapp.ui.component.AlertDialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.example.storidemoapp.ui.component.CustomButton.PrimaryButton
import com.example.storidemoapp.ui.theme.headline4
import com.example.storidemoapp.ui.theme.spacing
import com.example.storidemoapp.ui.theme.subtitle1
import com.example.storidemoapp.R

@Composable
fun ShowAlertDialog(alertDialogModel: AlertDialogModel) {
    alertDialogModel.apply {
        Dialog(
            onDismissRequest = {
                actionButton.invoke()
            }
        ) {
            CustomDialog(
                dialogType = dialogType,
                title = title,
                body = body,
                actionButton = { actionButton.invoke() },
            )
        }
    }
}

@Composable
fun CustomDialog(
    dialogType: AlertDialogType,
    title: String,
    body: String,
    actionButton: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(MaterialTheme.spacing.dimen16),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = MaterialTheme.spacing.dimen8)
    ) {
        Column(
            Modifier.padding(MaterialTheme.spacing.dimen16),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(
                    id = if (dialogType == AlertDialogType.SUCCESS)
                        R.drawable.ic_success
                    else
                        R.drawable.ic_error

                ),
                contentDescription = stringResource(
                    id = if (dialogType == AlertDialogType.SUCCESS)
                        R.string.success_dialog
                    else
                        R.string.error_dialog
                ),
            )
            Text(
                modifier = Modifier.padding(MaterialTheme.spacing.dimen16),
                text = title,
                style = headline4
            )


            Text(
                modifier = Modifier.padding(MaterialTheme.spacing.dimen16),
                text = body,
                style = subtitle1
            )

            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { actionButton.invoke() },
                text = stringResource(id = R.string.accept)
            )
        }
    }
}