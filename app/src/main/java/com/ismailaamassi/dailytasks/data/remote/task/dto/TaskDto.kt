package com.ismailaamassi.dailytasks.data.remote.task.dto

import com.squareup.moshi.Json

data class TaskDto(
    @field:Json(name = "title") val title: String,
    @field:Json(name = "createdAt") val createdAt: Long,
    @field:Json(name = "updatedAt") val updatedAt: Long,
    @field:Json(name = "userId") val userId: String,
    @field:Json(name = "id") val id: String,
)