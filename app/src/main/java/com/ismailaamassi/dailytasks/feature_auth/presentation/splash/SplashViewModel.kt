package com.ismailaamassi.dailytasks.feature_auth.presentation.splash

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ismailaamassi.dailytasks.core.util.Constants
import com.ismailaamassi.dailytasks.core.util.UiEvent
import com.ismailaamassi.dailytasks.feature_auth.presentation.destinations.LandingScreenDestination
import com.ismailaamassi.dailytasks.feature_auth.presentation.destinations.LoginScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    savedInstance: SavedStateHandle
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        Timber.tag("SplashViewModel").d(" init: ")
        viewModelScope.launch {
            delay(Constants.SPLASH_SCREEN_DELAY)
            _eventFlow.emit(UiEvent.Navigate(LoginScreenDestination))
        }
    }
}