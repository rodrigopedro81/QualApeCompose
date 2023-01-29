package com.qualapecompose.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.domain.commons.Verifier
import com.qualapecompose.R
import com.qualapecompose.Screen
import com.qualapecompose.customViews.*

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        val verifier = Verifier()
        val primaryButtonState = remember { mutableStateOf(false) }
        val emailText = remember { mutableStateOf("") }
        val emailIsValid = remember { mutableStateOf(false) }
        val password = remember { mutableStateOf("") }
        val passwordIsValid = remember { mutableStateOf(false) }
        VerticalSpacer(40.dp)
        SimpleHeader(text = "Você já é de casa!")
        VerticalSpacer(4.dp)
        SimpleCaption(text = "Fique à vontade")
        VerticalSpacer(26.dp)
        MainEditText(
            text = emailText,
            label = "Email",
            isValid = emailIsValid,
            leadingIcon = painterResource(id = R.drawable.ic_email),
            hint = "Digite seu e-mail",
            onTextChanged = { text ->
                emailIsValid.value = verifier.isEmailValid(text)
                primaryButtonState.value = emailIsValid.value && passwordIsValid.value
            }
        )
        VerticalSpacer(14.dp)
        MainEditText(
            text = password,
            label = "Senha",
            isValid = passwordIsValid,
            leadingIcon = painterResource(id = R.drawable.ic_password),
            hint = "Digite sua senha",
            onTextChanged = { text ->
                passwordIsValid.value = verifier.isPasswordValid(text)
                primaryButtonState.value =
                    emailIsValid.value && passwordIsValid.value
            }
        )
        VerticalSpacer(32.dp)
        PrimaryMainButton(
            modifier = Modifier.fillMaxWidth(),
            buttonText = "Entrar",
            onClick = {
                viewModel.login(
                    email = emailText.value,
                    password = password.value,
                    onComplete = { isSuccessful, errorMessage ->
                        if (isSuccessful) navController.navigate(Screen.Register.route)
                        else Log.d("Teste", "Falhou no login msg: $errorMessage")
                    }
                )
            },
            isButtonEnabled = primaryButtonState
        )
        VerticalSpacer(16.dp)
        SecondaryMainButton(
            modifier = Modifier.fillMaxWidth(),
            buttonText = "Criar minha conta",
            onClick = { navController.navigate(Screen.Register.route) },
        )
    }
}


@Preview
@Composable
fun LoginPreview() {
    LoginScreen(navController = rememberNavController())
}


