package com.ismailaamassi.dailytasks.core.util.task_checker

import com.ismailaamassi.dailytasks.core.util.Resource
import com.ismailaamassi.dailytasks.core.util.SimpleResource
import com.ismailaamassi.dailytasks.feature_task.data.local.TaskData

class DefaultTaskChecker: TaskChecker {

    override suspend fun toggleCheck(
        tasks: List<TaskData>,
        taskId: String,
        onRequest: suspend (isLiked: Boolean) -> SimpleResource,
        onStateUpdated: (List<TaskData>) -> Unit
    ) {
        val task = tasks.find { it.id == taskId }
        val currentlyChecked = task?.status == true
        val newTasks = tasks.map { taskData ->
            if (taskData.id == taskId) {
                taskData.copy(
                    status = !taskData.status,
                )
            } else taskData
        }
        onStateUpdated(newTasks)
        when (onRequest(currentlyChecked)) {
            is Resource.Success -> Unit
            is Resource.Error -> {
                val oldTasks = tasks.map { taskData ->
                    if (taskData.id == taskId) {
                        taskData.copy(
                            status = currentlyChecked,
                        )
                    } else taskData
                }
                onStateUpdated(oldTasks)
            }
        }
    }
}