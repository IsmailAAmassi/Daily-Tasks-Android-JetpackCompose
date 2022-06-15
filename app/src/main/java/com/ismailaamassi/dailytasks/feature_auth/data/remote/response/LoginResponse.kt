package com.ismailaamassi.dailytasks.feature_auth.data.remote.response

import com.squareup.moshi.Json

data class LoginResponse(@field:Json(name = "token") val token: String?)