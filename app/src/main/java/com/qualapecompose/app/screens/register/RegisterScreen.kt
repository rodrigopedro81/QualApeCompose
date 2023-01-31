package com.qualapecompose.app.screens.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.domain.commons.Verifier
import com.qualapecompose.app.navigation.Screen
import com.qualapecompose.app.utils.OnEachStateChanged
import com.qualapecompose.designSystem.*
import com.qualapecompose.ui.theme.QualApeComposeTheme

fun NavGraphBuilder.registerRoute(navController: NavHostController) {
    composable(route = Screen.Register.route) {
        RegisterScreen(navController)
    }
}

@Composable
fun RegisterScreen(
    navController: NavHostController,
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(16.dp)
    )
    {
        val nameText = remember { mutableStateOf("") }
        val nameIsValid = remember { mutableStateOf(false) }
        val emailText = remember { mutableStateOf("") }
        val emailIsValid = remember { mutableStateOf(false) }
        val whatsappText = remember { mutableStateOf("") }
        val whatsappIsValid = remember { mutableStateOf(false) }
        val apartmentText = remember { mutableStateOf("") }
        val apartmentIsValid = remember { mutableStateOf(false) }
        val blockText = remember { mutableStateOf("") }
        val blockIsValid = remember { mutableStateOf(false) }
        val primaryButtonState = remember { mutableStateOf(false) }
        listOf(
            nameIsValid,
            emailIsValid,
            whatsappIsValid,
            apartmentIsValid,
            blockIsValid
        ).OnEachStateChanged { observedStates, _ ->
            primaryButtonState.value = observedStates.all { it.value }
        }
        SimpleHeader(text = "Olá, morador")
        VerticalSpacer(dp = 4.dp)
        SimpleCaption(text = "Seus vizinhos estão esperando por você")
        VerticalSpacer(dp = 26.dp)
        MainEditText(
            text = nameText,
            label = "Nome",
            isValid = nameIsValid,
//            leadingIcon = painterResource(id = R.drawable.ic_email),
            hint = "Como você se chama?",
            onTextChanged = { nameIsValid.value = Verifier.isNameValid(it) }
        )
        VerticalSpacer(dp = 14.dp)
        MainEditText(
            text = emailText,
            label = "E-mail",
            isValid = emailIsValid,
//            leadingIcon = painterResource(id = R.drawable.ic_email),
            hint = "Digite seu e-mail",
            onTextChanged = { emailIsValid.value = Verifier.isEmailValid(it) }
        )
        VerticalSpacer(dp = 14.dp)
        MainEditText(
            text = whatsappText,
            label = "WhatsApp",
            isValid = whatsappIsValid,
//            leadingIcon = painterResource(id = R.drawable.ic_email),
            hint = "(00) 00000-0000",
            onTextChanged = { whatsappIsValid.value = Verifier.isWhatsappValid(it) }
        )
        VerticalSpacer(dp = 8.dp)
        Row(modifier = Modifier.fillMaxWidth()) {
            MainEditText(
                modifier = Modifier.fillMaxWidth(0.5f),
                text = apartmentText,
                label = "Número do apê",
                isValid = apartmentIsValid,
//            leadingIcon = painterResource(id = R.drawable.ic_email),
                hint = "000",
                onTextChanged = { apartmentIsValid.value = Verifier.isApartmentValid(it) }
            )
            HorizontalSpacer(dp = 16.dp)
            MainEditText(
                modifier = Modifier.fillMaxWidth(),
                text = blockText,
                label = "Bloco",
                isValid = blockIsValid,
//            leadingIcon = painterResource(id = R.drawable.ic_email),
                hint = "AA",
                onTextChanged = { blockIsValid.value = Verifier.isBlockValid(it) }
            )
        }
        VerticalSpacer(dp = 20.dp)
        PrimaryMainButton(
            buttonText = "Avançar",
            isButtonEnabled = primaryButtonState,
            onClick = {
                // Navegar para tela de confirmar senha
            }
        )
        VerticalSpacer(dp = 16.dp)
        SecondaryMainButton(
            buttonText = "Já tenho minha conta",
            onClick = { navController.popBackStack() }
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