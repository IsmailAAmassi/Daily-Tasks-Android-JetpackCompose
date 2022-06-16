package com.ismailaamassi.dailytasks.feature_auth.presentation.util

import com.ismailaamassi.dailytasks.core.util.Error


sealed class AuthError : Error() {
    object FieldEmpty : AuthError()
    object InputTooShort : AuthError()
    object InvalidEmail: AuthError()
    object InvalidPassword : AuthError()
}
