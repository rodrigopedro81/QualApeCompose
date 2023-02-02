package com.qualapecompose.app.screens.register

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.domain.commons.Verifier
import com.qualapecompose.app.screens.login.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel

class RegisterViewModel: ViewModel() {

    val nameTextFieldState by mutableStateOf(TextFieldState(onTextChange = ::updateName))
    val emailTextFieldState by mutableStateOf(TextFieldState(onTextChange = ::updateEmail))
    val whatsappTextFieldState by mutableStateOf(TextFieldState(onTextChange = ::updateWhatsapp))
    val apartmentTextFieldState by mutableStateOf(TextFieldState(onTextChange = ::updateApartment))
    val blockTextFieldState by mutableStateOf(TextFieldState(onTextChange = ::updateBlock))
    val createAccountButtonState by derivedStateOf {
        emailTextFieldState.isValid.value && nameTextFieldState.isValid.value
                && whatsappTextFieldState.isValid.value
                && apartmentTextFieldState.isValid.value
                && blockTextFieldState.isValid.value
    }

    private fun updateName(textFieldState: TextFieldState, newText: String) {
        textFieldState.apply {
            text.value = newText
            isValid.value = Verifier.isNameValid(newText)
        }
    }

    private fun updateEmail(textFieldState: TextFieldState, newText: String) {
        textFieldState.apply {
            text.value = newText
            isValid.value = Verifier.isEmailValid(newText)
        }
    }

    private fun updateWhatsapp(textFieldState: TextFieldState, newText: String) {
        textFieldState.apply {
            text.value = newText
            isValid.value = Verifier.isWhatsappValid(newText)
        }
    }

    private fun updateApartment(textFieldState: TextFieldState, newText: String) {
        textFieldState.apply {
            text.value = newText
            isValid.value = Verifier.isApartmentValid(newText)
        }
    }

    private fun updateBlock(textFieldState: TextFieldState, newText: String) {
        textFieldState.apply {
            text.value = newText
            isValid.value = Verifier.isBlockValid(newText)
        }
    }
}
