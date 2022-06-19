package com.ismailaamassi.dailytasks.feature_task.domain.use_case

import com.ismailaamassi.dailytasks.feature_task.data.local.TaskData
import com.ismailaamassi.dailytasks.feature_task.data.repository.FakeTaskRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before

class GetTasksUseCaseTest {

    private lateinit var getTasksUseCase: GetTasksUseCase
    private lateinit var fakeTaskRepository: FakeTaskRepository

    @Before
    fun setup() {
        fakeTaskRepository = FakeTaskRepository()
        getTasksUseCase = GetTasksUseCase(fakeTaskRepository)
        val tasksToInsert = mutableListOf<TaskData>()
        ('a'..'z').forEach {
            tasksToInsert.add(
                TaskData(
                    title = "",
                    description = "",
                    category = "",
                    status = false,
                    priority = 1,
                    time = -1,
                    createdAt = -1,
                    updatedAt = -1,
                    userId = "",
                    id = ""
                )
            )
        }
        tasksToInsert.shuffle()
        runBlocking {

        }
    }
}