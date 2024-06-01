package com.example.storidemoapp.ui.register.success

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.storidemoapp.R
import com.example.storidemoapp.ui.component.CustomButton.PrimaryButton
import com.example.storidemoapp.ui.theme.StoriDemoAppTheme
import com.example.storidemoapp.ui.theme.caption
import com.example.storidemoapp.ui.theme.headline5
import com.example.storidemoapp.ui.theme.spacing

@Composable
fun SuccessScreen(goToNextScreen: () -> Unit) =
    SuccessScreenContent(scrollState = rememberScrollState(), goToNextScreen = goToNextScreen)

@Composable
fun SuccessScreenContent(scrollState: ScrollState, goToNextScreen: () -> Unit) {
    Box(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)) {
        Image(
            modifier = Modifier.align(Alignment.TopCenter),
            painter = painterResource(R.drawable.done),
            contentDescription = stringResource(R.string.success_dialog),
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(MaterialTheme.spacing.dimen16),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier.padding(MaterialTheme.spacing.dimen16),
                text = stringResource(id = R.string.success_screen_title),
                style = headline5.copy(color = MaterialTheme.colorScheme.primary)
            )


            Text(
                modifier = Modifier.padding(MaterialTheme.spacing.dimen16),
                text = stringResource(id = R.string.success_screen_description),
                style = caption.copy(color = Color.Gray)
            )

            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { goToNextScreen.invoke() },
                text = stringResource(id = R.string.btn_continue)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SuccessScreenPreview() {
    StoriDemoAppTheme {
        SuccessScreenContent(scrollState = rememberScrollState(), goToNextScreen = {})
    }
}