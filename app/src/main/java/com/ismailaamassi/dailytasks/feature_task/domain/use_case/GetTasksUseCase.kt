package com.ismailaamassi.dailytasks.feature_task.domain.use_case

import com.ismailaamassi.dailytasks.core.util.Constants
import com.ismailaamassi.dailytasks.core.util.Resource
import com.ismailaamassi.dailytasks.feature_task.data.local.TaskData
import com.ismailaamassi.dailytasks.feature_task.domain.repository.TaskRepository

class GetTasksUseCase(
    private val repository: TaskRepository
) {

    suspend operator fun invoke(
        page: Int,
        pageSize: Int = Constants.DEFAULT_PAGE_SIZE
    ): Resource<List<TaskData>> {
        return repository.getTasks(page, pageSize)
    }
}