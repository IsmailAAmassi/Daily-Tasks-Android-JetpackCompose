package com.ismailaamassi.dailytasks.data.remote.user.dto

import com.squareup.moshi.Json

data class ProfileDto(
    @field:Json(name = "token") val token: String?,
    @field:Json(name = "email") val email: String?,
    @field:Json(name = "username") val username: String?,
    @field:Json(name = "image") val image: String?,
)