package com.example.storidemoapp.navigation.navHost

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.storidemoapp.navigation.routes.Arguments.REGISTER_FORM_MODEL
import com.example.storidemoapp.navigation.routes.NavigationItem
import com.example.storidemoapp.ui.home.activity.HomeActivity
import com.example.storidemoapp.ui.login.LoginScreen
import com.example.storidemoapp.ui.register.form.RegisterFormModel
import com.example.storidemoapp.ui.register.form.RegisterFormScreen
import com.example.storidemoapp.ui.register.picture.TakePhotoScreen
import com.example.storidemoapp.ui.register.success.SuccessScreen
import com.google.gson.Gson

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Login.route,
) {
    val context = LocalContext.current

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Login.route) {
            LoginScreen(
                onSuccessLogin = {
                    context.startActivity(Intent(context, HomeActivity::class.java))
                },
                goToSignup = {
                    navController.navigate(route = NavigationItem.RegisterForm.route)
                },
            )
        }

        composable(NavigationItem.RegisterForm.route) {
            RegisterFormScreen(
                backToLoginScreen = navController::popBackStack,
                goToNextScreen = { route ->
                    navController.navigate(route = route)
                })
        }

        composable(
            route = "${NavigationItem.UploadPhoto.route}/{$REGISTER_FORM_MODEL}",
            arguments = listOf(
                navArgument(REGISTER_FORM_MODEL) { type = NavType.StringType },
            )
        ) {
            val json = it.arguments?.getString(REGISTER_FORM_MODEL)
            val registerFormModel = Gson().fromJson(json, RegisterFormModel::class.java)
            TakePhotoScreen(registerFormModel = registerFormModel) {
                navController.navigate(route = NavigationItem.SuccessRegister.route)
            }
        }

        composable(NavigationItem.SuccessRegister.route) {
            SuccessScreen {
                context.startActivity(Intent(context, HomeActivity::class.java))
            }
        }
    }
}