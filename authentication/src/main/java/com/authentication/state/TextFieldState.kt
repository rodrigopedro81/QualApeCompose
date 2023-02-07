package com.authentication.state

import androidx.compose.runtime.Stable

@Stable
data class TextFieldState(
    val text: String = "",
    val isValid: Boolean = false,
)