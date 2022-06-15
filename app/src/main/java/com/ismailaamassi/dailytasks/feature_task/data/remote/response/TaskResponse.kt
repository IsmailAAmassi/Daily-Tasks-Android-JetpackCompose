package com.ismailaamassi.dailytasks.feature_task.data.remote.response

import com.squareup.moshi.Json

data class TaskResponse(
    @field:Json(name = "title") val title: String,
    @field:Json(name = "createdAt") val createdAt: Long,
    @field:Json(name = "updatedAt") val updatedAt: Long,
    @field:Json(name = "userId") val userId: String,
    @field:Json(name = "id") val id: String,
)