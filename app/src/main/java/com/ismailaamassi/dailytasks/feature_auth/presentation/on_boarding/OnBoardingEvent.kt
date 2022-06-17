package com.ismailaamassi.dailytasks.feature_auth.presentation.on_boarding

sealed class OnBoardingEvent {
    data class SaveOnBoardingState(val completed: Boolean) : OnBoardingEvent()
}