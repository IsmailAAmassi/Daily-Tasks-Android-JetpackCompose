package com.ismailaamassi.dailytasks.feature_auth.data.remote.response

import com.squareup.moshi.Json

data class RegisterResponse(@field:Json(name = "status") val status: Boolean)