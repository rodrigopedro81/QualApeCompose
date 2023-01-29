package com.qualapecompose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.qualapecompose.R
import com.qualapecompose.Screen
import com.qualapecompose.customViews.*
import com.qualapecompose.ui.theme.QualApeComposeTheme

@Composable
fun LoginScreen(navController: NavHostController) {
    QualApeComposeTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)
            ) {
                val buttonState = remember { mutableStateOf(true) }
                VerticalSpacer(40.dp)
                SimpleHeader(text = "Você já é de casa!")
                VerticalSpacer(4.dp)
                SimpleCaption(text = "Fique à vontade")
                VerticalSpacer(26.dp)
                MainEditText(
                    label = "Email",
                    leadingIcon = painterResource(id = R.drawable.ic_email),
                    placeholderText = "Digite seu e-mail",
                ) { text ->
                    text.length > 10
                }
                VerticalSpacer(14.dp)
                MainEditText(
                    label = "Senha",
                    leadingIcon = painterResource(id = R.drawable.ic_password),
                    placeholderText = "Digite sua senha",
                ) { text ->
                    text.length > 5
                }
                VerticalSpacer(32.dp)
                PrimaryMainButton(
                    modifier = Modifier.fillMaxWidth(),
                    buttonText = "Entrar",
                    onClick = { },
                    isButtonEnabled = buttonState
                )
                VerticalSpacer(16.dp)
                SecondaryMainButton(
                    modifier = Modifier.fillMaxWidth(),
                    buttonText = "Criar minha conta",
                    onClick = { navController.navigate(Screen.Register.route) },
                )
            }
        }
    }
}


@Preview
@Composable
fun LoginPreview() {
    LoginScreen(navController = rememberNavController())
}


