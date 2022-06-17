package com.ismailaamassi.dailytasks.feature_task.data.remote.request

data class CreateTaskRequest(
    val title: String,
    val description: String,
    val category: String,
    val priority: Int,
    val time: Long,
)