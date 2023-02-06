package com.domain.util

object Verifier {

    fun isWhatsappValid(string: String): Boolean = true

    fun isApartmentValid(string: String): Boolean {
        return if (string.toIntOrNull() != null) {
            (string.toInt() in 100..516)
        } else {
            false
        }
    }

    fun isBlockValid(string: String): Boolean {
        return if (string.toIntOrNull() != null) {
            (string.toInt() in 1..9)
        } else {
            false
        }
    }

    fun isEmailValid(string: String): Boolean = true

    fun isNameValid(string: String): Boolean =
        string.length > 6

    fun isPasswordValid(string: String): Boolean =
        string.length > 6
}