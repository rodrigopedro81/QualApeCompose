package com.qualapecompose.app.screens.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.qualapecompose.app.navigation.Screen
import com.qualapecompose.app.screens.login.TextFieldState
import com.qualapecompose.designSystem.*
import com.qualapecompose.designSystem.customWidgets.MainEditText
import com.qualapecompose.ui.theme.QualApeComposeTheme

fun NavGraphBuilder.registerRoute(navController: NavHostController) {
    composable(route = Screen.Register.route) {
//        RegisterScreen(navController)
    }
}
//
//@Composable
//fun RegisterScreen(
//    navController: NavHostController,
//    viewModel: RegisterViewModel = viewModel()
//) {
//    val nameState = viewModel.nameTextFieldState
//    val emailState = viewModel.emailTextFieldState
//    val whatsappState = viewModel.whatsappTextFieldState
//    val apartmentState = viewModel.apartmentTextFieldState
//    val blockState = viewModel.blockTextFieldState
//    val buttonIsEnabled = viewModel.createAccountButtonState
//
//    RegisterScreenContent(
//        nameState = nameState,
//        emailState = emailState,
//        whatsappState = whatsappState,
//        apartmentState = apartmentState,
//        blockState = blockState,
//        buttonIsEnabled = buttonIsEnabled,
//        goBack = { navController.popBackStack() }
//    )
//}
//
//@Composable
//fun RegisterScreenContent(
//    nameState: TextFieldState,
//    emailState: TextFieldState,
//    whatsappState: TextFieldState,
//    apartmentState: TextFieldState,
//    blockState: TextFieldState,
//    buttonIsEnabled: Boolean,
//    goBack: () -> Boolean
//) {
//    Column(
//        modifier = Modifier
//            .background(Color.White)
//            .fillMaxSize()
//            .padding(16.dp)
//    )
//    {
//        SimpleHeader(text = "Olá, morador")
//        VerticalSpacer(dp = 4.dp)
//        SimpleCaption(text = "Seus vizinhos estão esperando por você")
//        VerticalSpacer(dp = 26.dp)
//        NameTextField(nameState)
//        VerticalSpacer(dp = 14.dp)
//        EmailTextField(emailState)
//        VerticalSpacer(dp = 14.dp)
//        WhatsAppTextField(whatsappState)
//        VerticalSpacer(dp = 8.dp)
//        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
//            ApartmentTextField(apartmentState)
//            BlockTextField(blockState)
//        }
//        VerticalSpacer(dp = 20.dp)
//        PrimaryMainButton(
//            buttonText = "Avançar",
//            isButtonEnabled = buttonIsEnabled,
//            onClick = {
//                // Navegar para tela de confirmar senha
//            }
//        )
//        VerticalSpacer(dp = 16.dp)
//        SecondaryMainButton(
//            buttonText = "Já tenho minha conta",
//            onClick = { goBack() }
//        )
//    }
//}
//
//@Composable
//fun NameTextField(state: TextFieldState) {
//    MainEditText(
//        textFieldState = state,
//        label = "Nome",
////            leadingIcon = painterResource(id = R.drawable.ic_email),
//        hint = "Como você se chama?",
//    )
//}
//
//@Composable
//fun EmailTextField(state: TextFieldState) {
//    MainEditText(
//        textFieldState = state,
//        label = "E-mail",
////            leadingIcon = painterResource(id = R.drawable.ic_email),
//        hint = "Digite seu e-mail"
//    )
//}
//
//@Composable
//fun WhatsAppTextField(state: TextFieldState) {
//    MainEditText(
//        textFieldState = state,
//        label = "WhatsApp",
////            leadingIcon = painterResource(id = R.drawable.ic_email),
//        hint = "(00) 00000-0000",
//    )
//}
//
//@Composable
//fun ApartmentTextField(state: TextFieldState) {
//    MainEditText(
//        textFieldState = state,
//        modifier = Modifier.width(164.dp),
//        label = "Número do apê",
////            leadingIcon = painterResource(id = R.drawable.ic_email),
//        hint = "000",
//    )
//}
//
//@Composable
//fun BlockTextField(state: TextFieldState) {
//    MainEditText(
//        textFieldState = state,
//        modifier = Modifier.width(164.dp),
//        label = "Bloco",
////            leadingIcon = painterResource(id = R.drawable.ic_email),
//        hint = "AA",
//    )
//}
//
//@Preview
//@Composable
//fun RegisterScreenPreview() {
//    QualApeComposeTheme {
//        RegisterScreen(navController = rememberNavController())
//    }
//}