package com.authentication.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.authentication.firebaseauth.FirebaseAuthenticator
import com.authentication.state.LoginScreenState
import com.domain.routes.AuthenticationScreens
import com.designsystem.customWidgets.MainEditText
import com.designsystem.R.drawable.ic_email
import com.designsystem.R.drawable.ic_password
import com.designsystem.customWidgets.PrimaryMainButton
import com.designsystem.customWidgets.SecondaryMainButton
import com.qualapecompose.designSystem.*
import com.qualapecompose.ui.theme.QualApeComposeTheme

fun NavGraphBuilder.loginRoute(navController: NavHostController) {
    composable(route = AuthenticationScreens.Login.route) {
        LoginScreen(navController)
    }
}

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val loginScreenState by viewModel.uiState.collectAsStateWithLifecycle()
    LoginScreenContent(
        loginScreenState = loginScreenState,
        onEmailChange = viewModel::updateEmail,
        onPasswordChange = viewModel::updatePassword,
        navigateTo = { navController.navigate(it) },
        login = {
            viewModel.login { isSuccessful, errorMessage ->
//                if (isSuccessful) navController.navigate(Screen.Register.route) else errorMessage
            }
        },
    )
}

@Composable
fun LoginScreenContent(
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    loginScreenState: LoginScreenState,
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
            text = loginScreenState.emailState.text,
            onTextChange = onEmailChange,
            isValid = loginScreenState.emailState.isValid,
            startIcon = ic_email,
            hint = "Digite seu e-mail",
            label = "E-mail"
        )
        VerticalSpacer(14.dp)
        MainEditText(
            text = loginScreenState.passwordState.text,
            onTextChange = onPasswordChange,
            isValid = loginScreenState.passwordState.isValid,
            startIcon = ic_password,
            hint = "Digite sua senha",
            label = "Senha"
        )
        VerticalSpacer(32.dp)
        PrimaryMainButton(
            modifier = Modifier.fillMaxWidth(),
            buttonText = "Entrar",
            onClick = login,
            isButtonEnabled = loginScreenState.loginButtonEnabled
        )
        VerticalSpacer(16.dp)
        SecondaryMainButton(
            modifier = Modifier.fillMaxWidth(),
            buttonText = "Criar minha conta",
            onClick = { navigateTo(AuthenticationScreens.Register.route) },
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
