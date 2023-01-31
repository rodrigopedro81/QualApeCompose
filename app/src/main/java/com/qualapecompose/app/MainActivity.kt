package com.qualapecompose.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.qualapecompose.app.navigation.MainNavGraph
import com.qualapecompose.ui.theme.QualApeComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val x = Unit
    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QualApeComposeTheme {
                navController = rememberNavController()
                MainNavGraph(navController = navController)
            }
        }
    }
}