package com.ismailaamassi.dailytasks.feature_auth.presentation.on_boarding

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ismailaamassi.dailytasks.core.util.UiEvent
import com.ismailaamassi.dailytasks.destinations.LoginScreenDestination
import com.ismailaamassi.dailytasks.feature_auth.domain.use_case.SaveOnBoardingUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _eventFlow = MutableSharedFlow<UiEvent>()
    var eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(onBoardingEvent: OnBoardingEvent) {
        when (onBoardingEvent) {
            is OnBoardingEvent.SaveOnBoardingState -> saveOnBoardingState(onBoardingEvent.completed)
        }
    }

    private fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch {
            saveOnBoardingUseCase(completed)
            _eventFlow.emit(UiEvent.Navigate(LoginScreenDestination))
        }
    }
}