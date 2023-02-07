package com.authentication.login

import androidx.lifecycle.ViewModel
import com.authentication.firebaseauth.FirebaseAuthenticator
import com.authentication.state.LoginScreenState
import com.domain.util.Verifier
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

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

    fun updateEmail(newText: String) {
        _uiState.update { currentState ->
            currentState.copy(
                emailState = currentState.emailState.copy(
                    text = newText,
                    isValid = Verifier.isEmailValid(newText)
                )
            )
        }
        updateButtonState()
    }

    private fun updateButtonState() {
        _uiState.update { currentState ->
            currentState.copy(
                loginButtonEnabled = currentState.emailState.isValid
                        && currentState.passwordState.isValid
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
        updateButtonState()
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