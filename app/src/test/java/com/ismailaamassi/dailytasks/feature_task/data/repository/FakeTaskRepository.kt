package com.ismailaamassi.dailytasks.feature_task.data.repository

import com.ismailaamassi.dailytasks.core.util.Resource
import com.ismailaamassi.dailytasks.core.util.SimpleResource
import com.ismailaamassi.dailytasks.feature_task.data.local.TaskData
import com.ismailaamassi.dailytasks.feature_task.domain.repository.TaskRepository

class FakeTaskRepository : TaskRepository {

    private val tasks = mutableListOf<TaskData>()

    override suspend fun createTask(
        title: String,
        description: String,
        category: String,
        priority: Int,
        time: Long
    ): SimpleResource {
        TODO("Not yet implemented")
    }

    override suspend fun restoreTask(taskData: TaskData): SimpleResource {
        TODO("Not yet implemented")
    }

    override suspend fun getTasks(page: Int, pageSize: Int): Resource<List<TaskData>> {
        return Resource.Success(tasks)
    }

    override suspend fun checkTask(taskId: String): SimpleResource {
        TODO("Not yet implemented")
    }

    override suspend fun uncheckTask(taskId: String): SimpleResource {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(taskId: String): Resource<TaskData> {
        TODO("Not yet implemented")
    }

    override suspend fun updateTask(taskData: TaskData): SimpleResource {
        TODO("Not yet implemented")
    }

    override suspend fun getTask(taskId: String): Resource<TaskData> {
        TODO("Not yet implemented")
    }
}