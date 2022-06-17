package com.ismailaamassi.dailytasks.feature_task.domain.repository

import com.ismailaamassi.dailytasks.core.util.Resource
import com.ismailaamassi.dailytasks.core.util.SimpleResource
import com.ismailaamassi.dailytasks.feature_task.data.local.TaskData

interface TaskRepository {
    suspend fun createTask(
        title: String,
        description: String,
        category: String,
        priority: Int,
        time: Long
    ): SimpleResource

    suspend fun getTasks(page: Int, pageSize: Int): Resource<List<TaskData>>

}