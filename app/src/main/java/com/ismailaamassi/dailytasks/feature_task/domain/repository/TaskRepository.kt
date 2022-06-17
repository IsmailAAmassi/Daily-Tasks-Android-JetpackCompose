package com.ismailaamassi.dailytasks.feature_task.domain.repository

import com.ismailaamassi.dailytasks.core.util.SimpleResource

interface TaskRepository {
    suspend fun createTask(
        title: String,
        description: String,
        category: String,
        priority: Int,
        time: Long
    ): SimpleResource
}