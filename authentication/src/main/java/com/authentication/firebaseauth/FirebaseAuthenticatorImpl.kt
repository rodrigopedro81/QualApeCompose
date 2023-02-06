package com.authentication.firebaseauth

import com.domain.authentication.FirebaseAuthenticator
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseAuthenticatorImpl: FirebaseAuthenticator {

    private val auth = Firebase.auth

    override fun userIsAuthenticated() = auth.currentUser != null

    override fun userEmail() = auth.currentUser?.email

    override fun login(
        email:String,
        password:String,
        callback: (isSuccessful: Boolean, errorMessage: String?) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            callback.invoke(it.isSuccessful, it.exception?.message ?: UNKNOWN_ERROR)
        }
    }

    override fun register(
        email: String,
        password: String,
        callback: (isSuccessful: Boolean, errorMessage: String?) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            callback.invoke(it.isSuccessful, it.exception?.message ?: UNKNOWN_ERROR)
        }
    }

    companion object {
        private const val UNKNOWN_ERROR = "An Unknown error has occurred"
    }
}
