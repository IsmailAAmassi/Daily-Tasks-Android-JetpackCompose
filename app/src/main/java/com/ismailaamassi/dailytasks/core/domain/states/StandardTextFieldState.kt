package com.ismailaamassi.dailytasks.core.domain.states

import com.ismailaamassi.dailytasks.core.util.Error

data class StandardTextFieldState(
    val text: String = "",
    val error: Error? = null
)
