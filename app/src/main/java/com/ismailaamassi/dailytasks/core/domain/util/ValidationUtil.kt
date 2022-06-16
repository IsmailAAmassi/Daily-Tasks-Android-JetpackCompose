package com.ismailaamassi.dailytasks.core.domain.util

import android.util.Patterns
import com.ismailaamassi.dailytasks.core.util.Constants
import com.ismailaamassi.dailytasks.feature_auth.presentation.util.AuthError


object ValidationUtil {

    fun validateEmail(email: String): AuthError? {
        val trimmedEmail = email.trim()

        if(trimmedEmail.isBlank()) {
            return AuthError.FieldEmpty
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return AuthError.InvalidEmail
        }

        return null
    }

    fun validateEmailForLogin(email: String): AuthError? {
        val trimmedEmail = email.trim()

        if(trimmedEmail.isBlank()) {
            return AuthError.FieldEmpty
        }


        return null
    }

    fun validateUsername(username: String): AuthError? {
        val trimmedUsername = username.trim()
        if(trimmedUsername.isBlank()) {
            return AuthError.FieldEmpty
        }
        if(trimmedUsername.length < Constants.MIN_USERNAME_LENGTH) {
            return AuthError.InputTooShort
        }

        return null
    }

    fun validatePassword(password: String): AuthError? {
        val capitalLettersInPassword = password.any { it.isUpperCase() }
        val numberInPassword = password.any { it.isDigit() }

        if(password.isBlank()) {
            return AuthError.FieldEmpty
        }

        if(!capitalLettersInPassword || !numberInPassword) {
            return AuthError.InvalidPassword
        }


        if(password.length < Constants.MIN_PASSWORD_LENGTH) {
            return AuthError.InputTooShort
        }

        return null
    }

    fun validatePasswordForLogin(password: String): AuthError? {
        if(password.isBlank()) {
            return AuthError.FieldEmpty
        }
        return null
    }
}