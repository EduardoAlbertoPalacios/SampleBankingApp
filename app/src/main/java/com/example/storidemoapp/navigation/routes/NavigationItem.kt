package com.example.storidemoapp.navigation.routes

sealed class NavigationItem(val route: String) {
    object Home : NavigationItem(Screen.HOME.name)
    object Login : NavigationItem(Screen.LOGIN.name)
    object RegisterForm : NavigationItem(Screen.REGISTER_FORM.name)
    object UploadPhoto : NavigationItem(Screen.UPLOAD_PHOTO.name)
    object SuccessRegister : NavigationItem(Screen.UPLOAD_PHOTO.name)
}