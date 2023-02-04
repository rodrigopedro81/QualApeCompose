package com.qualapecompose.app.screens.login

import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.authentication.FirebaseAuthenticator
import com.domain.commons.Verifier
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@Stable
data class LoginScreenState(
    val emailState: TextFieldState = TextFieldState(),
    val passwordState: TextFieldState = TextFieldState(),
    val loginButtonEnabled: Boolean = false
)

@Stable
data class TextFieldState(
    val text: String = "",
    val isValid: Boolean = false,
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseAuthenticator: FirebaseAuthenticator,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginScreenState())
    val uiState: StateFlow<LoginScreenState> = _uiState.asStateFlow()

    fun login(
        onComplete: (isSuccessful: Boolean, errorMessage: String?) -> Unit
    ) {
        firebaseAuthenticator.login(
            email = _uiState.value.emailState.text,
            password = _uiState.value.passwordState.text,
            callback = onComplete
        )
    }
//
    fun updateEmail(newText: String) {
        _uiState.update { currentState ->
            currentState.copy(
                emailState = currentState.emailState.copy(
                    text = newText,
                    isValid = Verifier.isEmailValid(newText)
                )
            )
        }
    }

    fun updatePassword(newText: String) {
        _uiState.update { currentState ->
            currentState.copy(
                passwordState = currentState.passwordState.copy(
                    text = newText,
                    isValid = Verifier.isPasswordValid(newText)
                )
            )
        }
    }

    fun checkIfUserIsLoggedIn(
        onResult: (isLoggedIn: Boolean, userEmail: String?) -> Unit
    ) {
        onResult.invoke(firebaseAuthenticator.userIsAuthenticated(), getUserEmail())
    }

//    fun fetchUserDataForSessionModule(onComplete: (wasSuccessful: Boolean) -> Unit) {
//        getUserEmail()?.let {
//            firestoreRepository.fetchUserDataForSessionModule(it, onComplete)
//        }
//    }

    private fun getUserEmail() = firebaseAuthenticator.userEmail()

}