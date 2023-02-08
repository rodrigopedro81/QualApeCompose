package com.authentication.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.authentication.state.RegisterScreenState
import com.domain.routes.AuthenticationScreens
import com.designsystem.customWidgets.MainEditText
import com.designsystem.customWidgets.PrimaryMainButton
import com.designsystem.customWidgets.SecondaryMainButton
import com.qualapecompose.designSystem.SimpleCaption
import com.qualapecompose.designSystem.SimpleHeader
import com.qualapecompose.designSystem.VerticalSpacer
import com.qualapecompose.ui.theme.QualApeComposeTheme

fun NavGraphBuilder.registerRoute(navController: NavHostController) {
    composable(route = AuthenticationScreens.Register.route) {
        RegisterScreen(navController)
    }
}

@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val registerScreenState by viewModel.uiState.collectAsStateWithLifecycle()

    RegisterScreenContent(
        screenState = registerScreenState,
        onNameChange = viewModel::onNameChange,
        onEmailChange = viewModel::onEmailChange,
        onWhatsappChange = viewModel::onWhatsappChange,
        onApartmentChange = viewModel::onApartmentChange,
        onBlockChange = viewModel::onBlockChange,
        goBack = { navController.popBackStack() },
        advance = { navController.navigate(AuthenticationScreens.CreatePassword.route) }
    )
}

@Composable
fun RegisterScreenContent(
    screenState: RegisterScreenState,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onWhatsappChange: (String) -> Unit,
    onApartmentChange: (String) -> Unit,
    onBlockChange: (String) -> Unit,
    goBack: () -> Unit,
    advance: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(16.dp)
    )
    {
        SimpleHeader(text = "Olá, morador")
        VerticalSpacer(dp = 4.dp)
        SimpleCaption(text = "Seus vizinhos estão esperando por você")
        VerticalSpacer(dp = 26.dp)
        MainEditText(
            isValid = screenState.nameState.isValid,
            text = screenState.nameState.text,
            onTextChange = onNameChange,
            label = "Nome",
//            leadingIcon = painterResource(id = R.drawable.ic_email),
            hint = "Como você se chama?",
        )
        VerticalSpacer(dp = 14.dp)
        MainEditText(
            isValid = screenState.emailState.isValid,
            text = screenState.emailState.text,
            onTextChange = onEmailChange,
            label = "E-mail",
//            leadingIcon = painterResource(id = R.drawable.ic_email),
            hint = "Digite seu e-mail"
        )
        VerticalSpacer(dp = 14.dp)
        MainEditText(
            isValid = screenState.whatsappState.isValid,
            text = screenState.whatsappState.text,
            onTextChange = onWhatsappChange,
            label = "WhatsApp",
//            leadingIcon = painterResource(id = R.drawable.ic_email),
            hint = "(00) 00000-0000",
        )
        VerticalSpacer(dp = 8.dp)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            MainEditText(
                isValid = screenState.apartmentState.isValid,
                text = screenState.apartmentState.text,
                onTextChange = onApartmentChange,
                modifier = Modifier.width(164.dp),
                label = "Número do apê",
//            leadingIcon = painterResource(id = R.drawable.ic_email),
                hint = "000",
            )
            MainEditText(
                isValid = screenState.blockState.isValid,
                text = screenState.blockState.text,
                onTextChange = onBlockChange,
                modifier = Modifier.width(164.dp),
                label = "Bloco",
//            leadingIcon = painterResource(id = R.drawable.ic_email),
                hint = "AA",
            )
        }
        VerticalSpacer(dp = 20.dp)
        PrimaryMainButton(
            buttonText = "Avançar",
            isButtonEnabled = screenState.advanceButtonIsEnabled,
            onClick = {
                // Navegar para tela de confirmar senha
            }
        )
        VerticalSpacer(dp = 16.dp)
        SecondaryMainButton(
            buttonText = "Já tenho minha conta",
            onClick = { goBack() }
        )
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    QualApeComposeTheme {
        RegisterScreen(navController = rememberNavController())
    }
}