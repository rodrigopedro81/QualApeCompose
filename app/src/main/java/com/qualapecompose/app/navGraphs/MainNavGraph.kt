package com.qualapecompose.app.navGraphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.authentication.Screen
import com.authentication.login.loginRoute
import com.authentication.register.registerRoute

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
