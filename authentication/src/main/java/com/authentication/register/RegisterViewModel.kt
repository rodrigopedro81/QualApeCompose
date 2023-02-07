package com.authentication.register

import androidx.lifecycle.ViewModel
import com.authentication.state.RegisterScreenState
import com.domain.util.Verifier
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(): ViewModel() {

    private val _uiState = MutableStateFlow(RegisterScreenState())
    val uiState: StateFlow<RegisterScreenState> = _uiState.asStateFlow()

    fun onNameChange(newName: String) {
        _uiState.update { currentState ->
            currentState.copy(
                nameState = currentState.nameState.copy(
                    text = newName,
                    isValid = Verifier.isNameValid(newName)
                )
            )
        }
        updateButtonState()
    }

    fun onEmailChange(newEmail: String) {
        _uiState.update { currentState ->
            currentState.copy(
                emailState = currentState.emailState.copy(
                    text = newEmail,
                    isValid = Verifier.isEmailValid(newEmail)
                )
            )
        }
        updateButtonState()
    }

    fun onWhatsappChange(newWhatsapp: String) {
        _uiState.update { currentState ->
            currentState.copy(
                whatsappState = currentState.whatsappState.copy(
                    text = newWhatsapp,
                    isValid = Verifier.isWhatsappValid(newWhatsapp)
                )
            )
        }
        updateButtonState()
    }

    fun onApartmentChange(newApartment: String) {
        _uiState.update { currentState ->
            currentState.copy(
                apartmentState = currentState.apartmentState.copy(
                    text = newApartment,
                    isValid = Verifier.isApartmentValid(newApartment)
                )
            )
        }
        updateButtonState()
    }

    fun onBlockChange(newBlock: String) {
        _uiState.update { currentState ->
            currentState.copy(
                blockState = currentState.blockState.copy(
                    text = newBlock,
                    isValid = Verifier.isBlockValid(newBlock)
                )
            )
        }
        updateButtonState()
    }

    private fun updateButtonState() {
        _uiState.update { currentState ->
            currentState.copy(
                advanceButtonIsEnabled = currentState.emailState.isValid
                        && currentState.nameState.isValid && currentState.blockState.isValid
                        && currentState.apartmentState.isValid && currentState.whatsappState.isValid
            )
        }
    }
}
