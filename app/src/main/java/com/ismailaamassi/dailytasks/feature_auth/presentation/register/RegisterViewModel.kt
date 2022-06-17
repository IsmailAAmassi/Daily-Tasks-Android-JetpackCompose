package com.ismailaamassi.dailytasks.feature_auth.presentation.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ismailaamassi.dailytasks.R
import com.ismailaamassi.dailytasks.core.domain.states.PasswordTextFieldState
import com.ismailaamassi.dailytasks.core.domain.states.StandardTextFieldState
import com.ismailaamassi.dailytasks.core.util.Resource
import com.ismailaamassi.dailytasks.core.util.UiEvent
import com.ismailaamassi.dailytasks.core.util.UiText
import com.ismailaamassi.dailytasks.feature_auth.domain.use_case.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
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
        Timber.tag("RegisterViewModel").d("register : ")
        viewModelScope.launch {
            usernameState.value = usernameState.value.copy(error = null)
            emailState.value = emailState.value.copy(error = null)
            passwordState.value = passwordState.value.copy(error = null)

            loginState.value = loginState.value.copy(isLoading = true)
            delay(1000)
            val registerResult = registerUseCase(
                username = usernameState.value.text,
                email = emailState.value.text,
                password = passwordState.value.text
            )
            loginState.value = loginState.value.copy(isLoading = false)

            if (registerResult.usernameError != null) {
                usernameState.value = usernameState.value.copy(
                    error = registerResult.usernameError
                )
            }
            if (registerResult.emailError != null) {
                emailState.value = emailState.value.copy(
                    error = registerResult.emailError
                )
            }

            if (registerResult.passwordError != null) {
                passwordState.value = passwordState.value.copy(
                    error = registerResult.passwordError
                )
            }

            registerResult.result?.let {
                when (registerResult.result) {
                    is Resource.Success -> {
                        eventFlow.emit(
                            UiEvent.ShowToast(UiText.StringResource(R.string.create_account_successfully))
                        )
                        delay(100)
                        eventFlow.emit(UiEvent.PopBackStack)
                    }
                    is Resource.Error -> {
                        eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                registerResult.result.uiText ?: UiText.unknownError()
                            )
                        )
                    }
                }
            }
        }
    }
}