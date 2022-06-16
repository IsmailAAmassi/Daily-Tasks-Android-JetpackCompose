package com.ismailaamassi.dailytasks.core.util

import com.ismailaamassi.dailytasks.feature_auth.presentation.destinations.DirectionDestination

sealed class UiEvent {
    data class Navigate(val destination: DirectionDestination): UiEvent()
    data class ShowSnackbar(
        val uiText: UiText,
        val action: String? = null
    ): UiEvent()


    object PopBackStack: UiEvent()
    object OnLogin: UiEvent()
}