package com.domain.routes

sealed class AuthenticationScreens(val route: String) {
    object Login: AuthenticationScreens("login")
    object Register: AuthenticationScreens("register")
    object CreatePassword: AuthenticationScreens("create_password")
}
