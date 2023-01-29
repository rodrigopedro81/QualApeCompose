package com.qualapecompose

sealed class Screen(val route: String) {
    object Login: Screen("login")
    object Register: Screen("register")
    object CreatePassword: Screen("create_password")
}
