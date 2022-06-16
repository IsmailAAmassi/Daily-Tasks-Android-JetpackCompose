package com.ismailaamassi.dailytasks.feature_task.data.remote.response


data class TaskResponse(
    val title: String,
    val createdAt: Long,
    val updatedAt: Long,
    val userId: String,
    val id: String,
)