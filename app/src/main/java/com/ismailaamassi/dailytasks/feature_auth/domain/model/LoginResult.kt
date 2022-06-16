package com.ismailaamassi.dailytasks.feature_auth.domain.model

import com.ismailaamassi.dailytasks.core.util.SimpleResource
import com.ismailaamassi.dailytasks.feature_auth.presentation.util.AuthError


data class LoginResult(
    val emailError: AuthError? = null,
    val passwordError: AuthError? = null,
    val result: SimpleResource? = null
)
