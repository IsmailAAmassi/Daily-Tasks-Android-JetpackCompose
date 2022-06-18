package com.ismailaamassi.dailytasks.feature_task.domain.use_case

import com.ismailaamassi.dailytasks.core.domain.util.ValidationUtil
import com.ismailaamassi.dailytasks.feature_task.data.local.TaskData
import com.ismailaamassi.dailytasks.feature_task.domain.model.UpdateTaskResult
import com.ismailaamassi.dailytasks.feature_task.domain.repository.TaskRepository

class UpdateTaskUseCase(
    private val repository: TaskRepository
) {

    suspend operator fun invoke(
        taskData: TaskData
    ): UpdateTaskResult {
        val titleError = ValidationUtil.validateTaskTitle(taskData.title)
        val descriptionError = ValidationUtil.validateTaskDescription(taskData.description)
        val categoryError = ValidationUtil.validateTaskCategory(taskData.category)
        val priorityError = ValidationUtil.validateTaskPriority(taskData.priority)
        val timeError = ValidationUtil.validateTaskTime(taskData.time)

        if (
            titleError != null ||
            descriptionError != null ||
            categoryError != null ||
            priorityError != null ||
            timeError != null
        ) {
            return UpdateTaskResult(
                titleError = titleError,
                descriptionError = descriptionError,
                categoryError = categoryError,
                priorityError = priorityError,
                timeError = timeError,
            )
        }

        val updateTaskResult = repository.updateTask(taskData)
        return UpdateTaskResult(result = updateTaskResult)

    }
}