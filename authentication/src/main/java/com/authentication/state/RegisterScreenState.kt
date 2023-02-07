package com.authentication.state

import androidx.compose.runtime.Stable

@Stable
data class RegisterScreenState(
    val nameState: TextFieldState = TextFieldState(),
    val emailState: TextFieldState = TextFieldState(),
    val whatsappState: TextFieldState = TextFieldState(),
    val apartmentState: TextFieldState = TextFieldState(),
    val blockState: TextFieldState = TextFieldState(),
    val advanceButtonIsEnabled: Boolean = false,
)