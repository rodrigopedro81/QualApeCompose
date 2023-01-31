package com.qualapecompose.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.qualapecompose.app.screens.login.loginRoute
import com.qualapecompose.app.screens.register.registerRoute

@Composable
fun MainNavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ){
        loginRoute(navController)
        registerRoute(navController)
    }
}
