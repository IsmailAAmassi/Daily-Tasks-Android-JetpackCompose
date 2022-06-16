package com.ismailaamassi.dailytasks.feature_auth.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ismailaamassi.dailytasks.core.domain.states.PasswordTextFieldState
import com.ismailaamassi.dailytasks.core.domain.states.StandardTextFieldState
import com.ismailaamassi.dailytasks.core.util.Resource
import com.ismailaamassi.dailytasks.core.util.UiEvent
import com.ismailaamassi.dailytasks.core.util.UiText
import com.ismailaamassi.dailytasks.feature_auth.domain.use_case.LoginUseCase
import com.ismailaamassi.dailytasks.feature_auth.presentation.util.AuthError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    var emailState = mutableStateOf(StandardTextFieldState())
    private set

    var passwordState = mutableStateOf(PasswordTextFieldState())
    private set

    var loginState = mutableStateOf(LoginState())
    private set

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    var eventFlow = _eventFlow.asSharedFlow()


    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EnteredEmail -> {
                emailState.value = emailState.value.copy(text = event.email)
            }
            is LoginEvent.EnteredPassword -> {
                passwordState.value = passwordState.value.copy(text = event.password)
            }
            is LoginEvent.TogglePasswordVisibility -> {
                passwordState.value = passwordState.value.copy(
                    isPasswordVisible = !passwordState.value.isPasswordVisible
                )
            }
            is LoginEvent.Login -> {
                login()
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            emailState.value = emailState.value.copy(error = null)
            passwordState.value = passwordState.value.copy(error = null)

            loginState.value = loginState.value.copy(isLoading = true)
            delay(500)
            val loginResult = loginUseCase(
                email = emailState.value.text,
                password = passwordState.value.text
            )
            loginState.value = loginState.value.copy(isLoading = false)

            if (
                loginResult.emailError != null &&
                loginResult.emailError is AuthError.FieldEmpty
            ) {
                emailState.value = emailState.value.copy(
                    error = loginResult.emailError
                )
            }

            if (
                loginResult.passwordError != null &&
                loginResult.passwordError is AuthError.FieldEmpty
            ) {
                passwordState.value = passwordState.value.copy(
                    error = loginResult.passwordError
                )
            }

            loginResult.result?.let {
                when (loginResult.result) {
                    is Resource.Success -> {
                        Timber.tag("LoginViewModel").d("login : loginResult Success")
                        _eventFlow.emit(UiEvent.OnLogin)
                    }
                    is Resource.Error -> {
                        Timber.tag("LoginViewModel").d("login : loginResult Error")
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                loginResult.result.message ?: UiText.unknownError()
                            )
                        )
                    }
                }
            }
        }
    }
}