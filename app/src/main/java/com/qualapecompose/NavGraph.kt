package com.qualapecompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.qualapecompose.login.loginRoute
import com.qualapecompose.register.registerRoute

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
