package com.authentication.state

import androidx.compose.runtime.Stable

@Stable
data class LoginScreenState(
    val emailState: TextFieldState = TextFieldState(),
    val passwordState: TextFieldState = TextFieldState(),
    val loginButtonEnabled: Boolean = false
)