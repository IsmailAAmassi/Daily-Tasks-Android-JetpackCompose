package com.ismailaamassi.dailytasks.feature_task.data.remote.request

data class UpdateTaskRequest(
    val taskId: String,
    val title: String,
    val description: String,
    val category: String,
    val priority: Int,
    val time: Long,
)