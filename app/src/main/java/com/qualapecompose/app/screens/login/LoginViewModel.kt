package com.qualapecompose.app.screens.login

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.authentication.FirebaseAuthenticator
import com.domain.commons.Verifier
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseAuthenticator: FirebaseAuthenticator,
) : ViewModel() {

    val emailTextFieldState by mutableStateOf(TextFieldState(onTextChange = ::updateEmailTextFieldState))
    val passwordTextFieldState by mutableStateOf(TextFieldState(onTextChange = ::updatePasswordTextFieldState))
    val loginButtonState by derivedStateOf { emailTextFieldState.isValid.value && passwordTextFieldState.isValid.value }

    fun login(
        onComplete: (isSuccessful: Boolean, errorMessage: String?) -> Unit
    ) {
        firebaseAuthenticator.login(
            email = emailTextFieldState.text.value,
            password = passwordTextFieldState.text.value,
            callback = onComplete
        )
    }

    private fun updateEmailTextFieldState(textFieldState: TextFieldState, newText: String) {
        textFieldState.apply {
            text.value = newText
            isValid.value = Verifier.isEmailValid(newText)
        }
    }

    private fun updatePasswordTextFieldState(textFieldState: TextFieldState, newText: String) {
        textFieldState.apply {
            text.value = newText
            isValid.value = Verifier.isPasswordValid(newText)
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