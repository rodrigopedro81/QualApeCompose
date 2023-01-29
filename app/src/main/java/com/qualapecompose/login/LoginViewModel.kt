package com.qualapecompose.login

import androidx.lifecycle.ViewModel
import com.authentication.FirebaseAuthenticator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseAuthenticator: FirebaseAuthenticator,
): ViewModel() {

    fun login(
        email: String,
        password: String,
        onComplete: (isSuccessful: Boolean, errorMessage: String?) -> Unit
    ) {
        firebaseAuthenticator.login(
            email = email,
            password = password,
            callback = onComplete
        )
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