package com.qualapecompose.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.qualapecompose.Screen

fun NavGraphBuilder.registerRoute(navController: NavHostController) {
    composable(route = Screen.Register.route){
        RegisterScreen(navController)
    }
}

@Composable
fun RegisterScreen(navController: NavHostController) {
    Box {
        Text(
            text = "TELA DE REGISTRO",
            modifier = Modifier.clickable {
                navController.navigate(Screen.Login.route)
            }
        )
    }
}