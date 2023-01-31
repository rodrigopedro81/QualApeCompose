package com.qualapecompose.app.screens.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
        NameTextField(nameText, nameIsValid)
        VerticalSpacer(dp = 14.dp)
        EmailTextField(emailText, emailIsValid)
        VerticalSpacer(dp = 14.dp)
        WhatsAppTextField(whatsappText, whatsappIsValid)
        VerticalSpacer(dp = 8.dp)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            ApartmentTextField(apartmentText, apartmentIsValid)
            BlockTextField(blockText, blockIsValid)
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

@Composable
fun NameTextField(
    nameText: MutableState<String>,
    nameIsValid: MutableState<Boolean>
) {
    MainEditText(
        text = nameText,
        label = "Nome",
        isValid = nameIsValid,
//            leadingIcon = painterResource(id = R.drawable.ic_email),
        hint = "Como você se chama?",
        onTextChanged = { nameIsValid.value = Verifier.isNameValid(it) }
    )
}

@Composable
fun EmailTextField(
    emailText: MutableState<String>,
    emailIsValid: MutableState<Boolean>
) {
    MainEditText(
        text = emailText,
        label = "E-mail",
        isValid = emailIsValid,
//            leadingIcon = painterResource(id = R.drawable.ic_email),
        hint = "Digite seu e-mail",
        onTextChanged = { emailIsValid.value = Verifier.isEmailValid(it) }
    )
}

@Composable
fun WhatsAppTextField(
    whatsappText: MutableState<String>,
    whatsappIsValid: MutableState<Boolean>
) {
    MainEditText(
        text = whatsappText,
        label = "WhatsApp",
        isValid = whatsappIsValid,
//            leadingIcon = painterResource(id = R.drawable.ic_email),
        hint = "(00) 00000-0000",
        onTextChanged = { whatsappIsValid.value = Verifier.isWhatsappValid(it) }
    )
}

@Composable
fun ApartmentTextField(
    apartmentText: MutableState<String>,
    apartmentIsValid: MutableState<Boolean>
) {
    MainEditText(
        modifier = Modifier.width(164.dp),
        text = apartmentText,
        label = "Número do apê",
        isValid = apartmentIsValid,
//            leadingIcon = painterResource(id = R.drawable.ic_email),
        hint = "000",
        onTextChanged = { apartmentIsValid.value = Verifier.isApartmentValid(it) }
    )
}

@Composable
fun BlockTextField(
    blockText: MutableState<String>,
    blockIsValid: MutableState<Boolean>
) {
    MainEditText(
        modifier = Modifier.width(164.dp),
        text = blockText,
        label = "Bloco",
        isValid = blockIsValid,
//            leadingIcon = painterResource(id = R.drawable.ic_email),
        hint = "AA",
        onTextChanged = { blockIsValid.value = Verifier.isBlockValid(it) }
    )
}

@Preview
@Composable
fun RegisterScreenPreview() {
    QualApeComposeTheme {
        RegisterScreen(navController = rememberNavController())
    }
}