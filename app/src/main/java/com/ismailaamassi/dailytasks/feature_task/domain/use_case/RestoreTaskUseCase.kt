package com.ismailaamassi.dailytasks.feature_task.domain.use_case

import com.ismailaamassi.dailytasks.core.util.SimpleResource
import com.ismailaamassi.dailytasks.feature_task.data.local.TaskData
import com.ismailaamassi.dailytasks.feature_task.domain.repository.TaskRepository

class RestoreTaskUseCase(
    private val repository: TaskRepository
) {

    suspend operator fun invoke(
        taskData: TaskData,
    ): SimpleResource {
        return repository.restoreTask(taskData)
    }
}