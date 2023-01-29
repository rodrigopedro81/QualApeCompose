package com.qualapecompose.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.qualapecompose.Screen

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