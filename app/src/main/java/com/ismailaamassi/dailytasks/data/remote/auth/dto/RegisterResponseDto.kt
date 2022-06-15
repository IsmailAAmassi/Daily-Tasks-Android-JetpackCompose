package com.ismailaamassi.dailytasks.data.remote.auth.dto

import com.squareup.moshi.Json

data class RegisterResponseDto(@field:Json(name = "status") val status: Boolean)