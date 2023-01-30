package com.qualapecompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.qualapecompose.login.LoginScreen
import com.qualapecompose.register.RegisterScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ){
        composable(route = Screen.Login.route){
            LoginScreen(navController)
        }
        composable(route = Screen.Register.route){
            RegisterScreen(navController)
        }
        composable(route = Screen.CreatePassword.route){
            // Put the screen here
        }
    }
}
