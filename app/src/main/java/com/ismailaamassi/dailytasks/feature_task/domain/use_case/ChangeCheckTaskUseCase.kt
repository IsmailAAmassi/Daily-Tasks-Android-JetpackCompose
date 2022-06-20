package com.ismailaamassi.dailytasks.feature_task.domain.use_case

import com.ismailaamassi.dailytasks.core.util.SimpleResource
import com.ismailaamassi.dailytasks.feature_task.domain.repository.TaskRepository

/**
 * this useCase return error if ...
 * ...network connection error
 * ...server return error
 * */
class ChangeCheckTaskUseCase(
    private val repository: TaskRepository
) {

    suspend operator fun invoke(
        taskId: String,
        isChecked: Boolean
    ): SimpleResource {
        return if (isChecked) {
            repository.uncheckTask(taskId)
        } else {
            repository.checkTask(taskId)
        }
    }
}