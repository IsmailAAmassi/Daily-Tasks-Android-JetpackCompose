package com.ismailaamassi.dailytasks.feature_auth.presentation.login

sealed class LoginEvent {
    data class EnteredEmail(val email: String): LoginEvent()
    data class EnteredPassword(val password: String): LoginEvent()
    object Login: LoginEvent()
    object TogglePasswordVisibility: LoginEvent()
}