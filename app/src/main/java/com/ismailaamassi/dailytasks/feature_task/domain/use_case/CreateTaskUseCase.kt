package com.ismailaamassi.dailytasks.feature_task.domain.use_case

import com.ismailaamassi.dailytasks.core.domain.util.ValidationUtil
import com.ismailaamassi.dailytasks.feature_task.domain.model.CreateTaskResult
import com.ismailaamassi.dailytasks.feature_task.domain.repository.TaskRepository

/**
 * This useCase will return Error if ...
 * ...the title,category are empty
 * ...the priority less than 1 or more than 3
 * ...the time is older the current time
 * ...the server throw HttpException
 * ...the server throw IOException
 * */
class CreateTaskUseCase(
    private val repository: TaskRepository
) {

    suspend operator fun invoke(
        title: String,
        description: String,
        category: String,
        priority: Int = 1,
        time: Long = -1L
    ): CreateTaskResult {
        val titleError = ValidationUtil.validateTaskTitle(title)
        val descriptionError = ValidationUtil.validateTaskDescription(description)
        val categoryError = ValidationUtil.validateTaskCategory(category)
        val priorityError = ValidationUtil.validateTaskPriority(priority)
        val timeError = ValidationUtil.validateTaskTime(time)

        if (
            titleError != null ||
            descriptionError != null ||
            categoryError != null ||
            priorityError != null ||
            timeError != null
        ) {
            return CreateTaskResult(
                titleError = titleError,
                descriptionError = descriptionError,
                categoryError = categoryError,
                priorityError = priorityError,
                timeError = timeError,
            )
        }

        val createTaskResult = repository.createTask(
            title = title,
            description = description,
            category = category,
            priority = priority,
            time = time,
        )

        return CreateTaskResult(result = createTaskResult)
    }
}