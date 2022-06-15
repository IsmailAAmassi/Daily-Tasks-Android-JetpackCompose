package com.ismailaamassi.dailytasks.data.remote.auth.dto

import com.squareup.moshi.Json

data class LoginResponseDto(@field:Json(name = "token") val token: String?)