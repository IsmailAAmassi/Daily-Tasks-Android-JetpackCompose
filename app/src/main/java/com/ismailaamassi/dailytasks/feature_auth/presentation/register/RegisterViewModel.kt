package com.ismailaamassi.dailytasks.feature_auth.presentation.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ismailaamassi.dailytasks.core.domain.states.PasswordTextFieldState
import com.ismailaamassi.dailytasks.core.util.UiEvent
import com.ismailaamassi.dailytasks.core.domain.states.StandardTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var usernameState = mutableStateOf(StandardTextFieldState())
        private set
    var emailState = mutableStateOf(StandardTextFieldState())
        private set
    var passwordState = mutableStateOf(PasswordTextFieldState())
        private set
    var loginState = mutableStateOf(RegisterState())
        private set
    var eventFlow = MutableSharedFlow<UiEvent>()
        private set

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EnteredUsername -> {
                usernameState.value = usernameState.value.copy(text = event.username)
            }
            is RegisterEvent.EnteredEmail -> {
                emailState.value = emailState.value.copy(text = event.email)
            }
            is RegisterEvent.EnteredPassword -> {
                passwordState.value = passwordState.value.copy(text = event.password)
            }
            is RegisterEvent.TogglePasswordVisibility -> {
                passwordState.value = passwordState.value.copy(
                    isPasswordVisible = !passwordState.value.isPasswordVisible
                )
            }
            is RegisterEvent.Register -> {
                register()
            }
        }
    }

    private fun register() {
        viewModelScope.launch {
            usernameState.value = emailState.value.copy(error = null)
            emailState.value = emailState.value.copy(error = null)
            passwordState.value = passwordState.value.copy(error = null)

            loginState.value = loginState.value.copy(isLoading = true)
            delay(1000)
            loginState.value = loginState.value.copy(isLoading = false)
        }
    }
}