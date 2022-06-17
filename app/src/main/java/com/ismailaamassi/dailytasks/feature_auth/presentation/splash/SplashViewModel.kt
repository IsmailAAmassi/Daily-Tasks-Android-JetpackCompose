package com.ismailaamassi.dailytasks.feature_auth.presentation.splash

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ismailaamassi.dailytasks.core.util.Constants
import com.ismailaamassi.dailytasks.core.util.Resource
import com.ismailaamassi.dailytasks.core.util.UiEvent
import com.ismailaamassi.dailytasks.destinations.LoginScreenDestination
import com.ismailaamassi.dailytasks.destinations.OnBoardingScreenDestination
import com.ismailaamassi.dailytasks.destinations.TaskListScreenDestination
import com.ismailaamassi.dailytasks.feature_auth.domain.use_case.AuthenticateUseCase
import com.ismailaamassi.dailytasks.feature_auth.domain.use_case.ReadOnBoardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    private val readOnBoardingUseCase: ReadOnBoardingUseCase,
    private val authenticateUseCase: AuthenticateUseCase,
    savedInstance: SavedStateHandle
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            delay(Constants.SPLASH_SCREEN_DELAY)
            readOnBoardingUseCase().collect { completed ->
                if (completed) {
                    when(authenticateUseCase()) {
                        is Resource.Success -> {
                            _eventFlow.emit(UiEvent.Navigate(TaskListScreenDestination))
                        }
                        is Resource.Error -> {
                            _eventFlow.emit(UiEvent.Navigate(LoginScreenDestination))
                        }
                    }
                } else {
                    _eventFlow.emit(UiEvent.Navigate(OnBoardingScreenDestination))
                }
            }

        }
    }
}