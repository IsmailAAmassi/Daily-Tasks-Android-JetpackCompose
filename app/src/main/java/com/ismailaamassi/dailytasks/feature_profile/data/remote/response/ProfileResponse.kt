package com.ismailaamassi.dailytasks.feature_profile.data.remote.response

import com.squareup.moshi.Json

data class ProfileResponse(
    @field:Json(name = "token") val token: String?,
    @field:Json(name = "email") val email: String?,
    @field:Json(name = "username") val username: String?,
    @field:Json(name = "image") val image: String?,
)