package com.ismailaamassi.dailytasks.core.util.task_checker

import com.ismailaamassi.dailytasks.core.util.SimpleResource
import com.ismailaamassi.dailytasks.feature_task.data.local.TaskData

interface TaskChecker {

    suspend fun toggleCheck(
        tasks: List<TaskData>,
        taskId: String,
        onRequest: suspend (isLiked: Boolean) -> SimpleResource,
        onStateUpdated: (List<TaskData>) -> Unit
    )
}