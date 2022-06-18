package com.ismailaamassi.dailytasks.feature_auth.data.remote.response

data class AuthResponse(
    val userId: String,
    val userName: String,
    val email: String,
    val token: String
)
