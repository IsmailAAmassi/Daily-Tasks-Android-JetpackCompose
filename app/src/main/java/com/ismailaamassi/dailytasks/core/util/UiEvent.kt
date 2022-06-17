package com.ismailaamassi.dailytasks.core.util

import com.ismailaamassi.dailytasks.destinations.DirectionDestination

sealed class UiEvent: Event() {
    data class Navigate(val destination: DirectionDestination): UiEvent()
    data class ShowSnackbar(
        val uiText: UiText,
        val action: String? = null
    ): UiEvent()

    data class ShowToast(
        val uiText: UiText,
    ): UiEvent()


    object PopBackStack: UiEvent()
    object OnLogin: UiEvent()
}