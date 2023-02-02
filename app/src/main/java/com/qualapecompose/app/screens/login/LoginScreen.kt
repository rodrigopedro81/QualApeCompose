package com.qualapecompose.app.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.authentication.FirebaseAuthenticator
import com.qualapecompose.R
import com.qualapecompose.app.navigation.Screen
import com.qualapecompose.designSystem.*
import com.qualapecompose.designSystem.customWidgets.MainEditText
import com.qualapecompose.ui.theme.QualApeComposeTheme

fun NavGraphBuilder.loginRoute(navController: NavHostController) {
    composable(route = Screen.Login.route) {
        LoginScreen(navController)
    }
}

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val emailState = viewModel.emailTextFieldState
    val passwordState = viewModel.passwordTextFieldState
    val loginButtonState = viewModel.loginButtonState

    LoginScreenContent(
        buttonState = loginButtonState,
        emailState = emailState,
        passwordState = passwordState,
        navigateTo = { navController.navigate(it) },
        login = {
            viewModel.login { isSuccessful, errorMessage ->
//                NAVEGAR PARA HOME
//                navController.navigate(Screen.)
            }
        }
    )
}

@Stable
class TextFieldState(
    initialText: String = "",
    onTextChange: ((state: TextFieldState, newText: String) -> Unit)? = null,
) {
    val isValid: MutableState<Boolean> = mutableStateOf(false)
    val text: MutableState<String> = mutableStateOf(initialText)
    val onTextChange: (TextFieldState, String) -> Unit = onTextChange ?: { state, newText ->
        text.value = newText
    }
}

@Composable
fun LoginScreenContent(
    buttonState: Boolean,
    emailState: TextFieldState,
    passwordState: TextFieldState,
    navigateTo: (route: String) -> Unit,
    login: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        VerticalSpacer(40.dp)
        SimpleHeader(text = "Você já é de casa!")
        VerticalSpacer(4.dp)
        SimpleCaption(text = "Fique à vontade")
        VerticalSpacer(26.dp)
        MainEditText(
            textFieldState = emailState,
            startIcon = R.drawable.ic_email,
            hint = "Digite seu e-mail",
            label = "E-mail"
        )
        VerticalSpacer(14.dp)
        MainEditText(
            textFieldState = passwordState,
            startIcon = R.drawable.ic_password,
            hint = "Digite sua senha",
            label = "Senha"
        )
        VerticalSpacer(32.dp)
        PrimaryMainButton(
            modifier = Modifier.fillMaxWidth(),
            buttonText = "Entrar",
            onClick = login,
            isButtonEnabled = buttonState
        )
        VerticalSpacer(16.dp)
        SecondaryMainButton(
            modifier = Modifier.fillMaxWidth(),
            buttonText = "Criar minha conta",
            onClick = { navigateTo(Screen.Register.route) },
        )
    }
}


@Preview
@Composable
fun LoginPreview() {
    QualApeComposeTheme {
        LoginScreen(
            navController = rememberNavController(),
            LoginViewModel(object : FirebaseAuthenticator {
                override fun userIsAuthenticated(): Boolean = true
                override fun userEmail(): String = ""
                override fun login(
                    email: String,
                    password: String,
                    callback: (isSuccessful: Boolean, errorMessage: String?) -> Unit
                ) {
                }

                override fun register(
                    email: String,
                    password: String,
                    callback: (isSuccessful: Boolean, errorMessage: String?) -> Unit
                ) {
                }
            })
        )
    }
}


