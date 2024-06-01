package com.example.storidemoapp.ui.register.picture

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.shared.commonResult.ResponseType
import com.example.shared.extensions.notNull
import com.example.storidemoapp.R
import com.example.storidemoapp.ui.component.AlertDialog.AlertDialogModel
import com.example.storidemoapp.ui.component.AlertDialog.AlertDialogType
import com.example.storidemoapp.ui.component.AlertDialog.ShowAlertDialog
import com.example.storidemoapp.ui.component.CustomButton.PrimaryButton
import com.example.storidemoapp.ui.component.ProgressBar.StoriProgressBar
import com.example.storidemoapp.ui.register.form.RegisterFormModel
import com.example.storidemoapp.ui.theme.StoriDemoAppTheme
import com.example.storidemoapp.ui.theme.caption
import com.example.storidemoapp.ui.theme.headline5
import com.example.storidemoapp.ui.theme.spacing
import com.example.storidemoapp.ui.theme.subtitle1

@Composable
fun TakePhotoScreen(
    registerFormModel: RegisterFormModel,
    viewModel: TakePhotoViewModel = hiltViewModel(),
    goToNextScreen: () -> Unit,
) =
    viewModel.apply {
        val state by state.collectAsState()

        TakePhotoScreenContent(
            state = state,
            registerFormModel = registerFormModel,
            updatePhotoUnavailable = this::updatePhotoUnavailable,
            updateCaptureImagePath = this::updateCaptureImagePath,
            updateCaptureImageUri = this::updateCaptureImageUri,
            captureImageUri = captureImageUri,
            takePhoto = this::takePhoto,
            registerUser = this::verifyInputs,
            updateResponseTypeToNone = this::updateResponseTypeToNone,
            goToNextScreen = goToNextScreen
        )
    }

@Composable
fun TakePhotoScreenContent(
    state: TakePhotoState,
    registerFormModel: RegisterFormModel,
    updatePhotoUnavailable: () -> Unit,
    updateCaptureImagePath: (String) -> Unit,
    updateCaptureImageUri: (Uri) -> Unit,
    captureImageUri: Uri,
    takePhoto: (Boolean) -> Unit,
    registerUser:(RegisterFormModel) -> Unit,
    updateResponseTypeToNone: () -> Unit,
    goToNextScreen: () -> Unit,
) {
    Box(
        Modifier
            .padding(MaterialTheme.spacing.dimen24)
            .fillMaxSize()
    ) {

        Image(
            painter = if (captureImageUri.path?.isNotEmpty() == true) {
                rememberImagePainter(captureImageUri)
            } else {
                painterResource(id = R.drawable.image_upload_file)
            },
            contentDescription = stringResource(id = R.string.image_upload_file),
            Modifier.align(Alignment.TopCenter)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text(
                text = stringResource(id = R.string.upload_a_photo_title),
                modifier = Modifier.padding(top = MaterialTheme.spacing.dimen16),
                style = headline5.copy(color = MaterialTheme.colorScheme.primary)
            )

            Text(
                text = stringResource(id = R.string.upload_a_photo_description),
                modifier = Modifier.padding(top = MaterialTheme.spacing.dimen16),
                style = caption.copy(color = Color.Gray)
            )

            PrimaryButton(
                onClick = { takePhoto.invoke(true) },
                text = stringResource(id = R.string.take_a_photo),
                modifier = Modifier
                    .padding(top = MaterialTheme.spacing.dimen16)
                    .fillMaxWidth(),
            )

            TextButton(
                onClick = { registerUser.invoke(registerFormModel) },
            ) {
                Text(
                    text = stringResource(R.string.btn_continue),
                    style = subtitle1.copy(
                        MaterialTheme.colorScheme.background,
                    )
                )
            }
        }
    }

    if (state.isTakePhotoPressed) {
        CapturePhotoDataManager(
            onSuccess = { uriImages ->
                updateCaptureImagePath.invoke(uriImages.path.notNull())
                updateCaptureImageUri.invoke(uriImages)
                takePhoto(false)
            }, onError = {
                takePhoto(false)
            }
        )
    }

    if (state.isPhotoUnavailable) {
        ShowMessageError(
            message = stringResource(id = R.string.take_a_photo_error_description,),
            actionButton = updatePhotoUnavailable
        )
    }

    when (state.responseType) {
        ResponseType.LOADING -> StoriProgressBar()

        ResponseType.ERROR -> ShowMessageError(
            message = state.responseMessageError,
            actionButton = updateResponseTypeToNone
        )

        ResponseType.SUCCESS -> goToNextScreen.invoke()

        else -> Unit
    }
}

@Composable
private fun ShowMessageError(message: String, actionButton: () -> Unit) = ShowAlertDialog(
    alertDialogModel = AlertDialogModel(
        AlertDialogType.ERROR,
        title = stringResource(id = R.string.take_a_photo_error),
        body = message,
        actionButton = actionButton
    )
)

@Preview(showBackground = true)
@Composable
fun RegisterFormScreenContentPreview() {
    StoriDemoAppTheme {
        TakePhotoScreenContent(
            state = TakePhotoState(),
            registerFormModel = RegisterFormModel(),
            updatePhotoUnavailable = {},
            updateCaptureImagePath = {},
            updateCaptureImageUri = {},
            captureImageUri = Uri.EMPTY,
            takePhoto = {},
            registerUser = {},
            updateResponseTypeToNone = {},
            goToNextScreen = {}
        )
    }
}