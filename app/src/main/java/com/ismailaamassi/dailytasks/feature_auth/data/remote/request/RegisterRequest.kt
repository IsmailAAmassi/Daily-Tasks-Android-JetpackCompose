package com.ismailaamassi.dailytasks.feature_auth.data.remote.request

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)
