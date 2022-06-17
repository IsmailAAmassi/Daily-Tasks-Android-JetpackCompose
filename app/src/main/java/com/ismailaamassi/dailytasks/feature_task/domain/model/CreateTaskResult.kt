package com.ismailaamassi.dailytasks.feature_task.domain.model

import com.ismailaamassi.dailytasks.core.util.SimpleResource
import com.ismailaamassi.dailytasks.feature_task.presentation.util.TaskError

data class CreateTaskResult(
    val titleError: TaskError? = null,
    val descriptionError: TaskError? = null,
    val categoryError: TaskError? = null,
    val priorityError: TaskError? = null,
    val timeError: TaskError? = null,
    val result: SimpleResource? = null
)
