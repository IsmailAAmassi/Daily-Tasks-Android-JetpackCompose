package com.ismailaamassi.dailytasks.feature_task.domain.use_case

data class TaskUseCases(
    val changeCheckTaskUseCase: ChangeCheckTaskUseCase,
    val createTaskUseCase: CreateTaskUseCase,
    val deleteTaskUseCase: DeleteTaskUseCase,
    val getTasksUseCase: GetTasksUseCase,
    val getTaskDetailsUseCase: GetTaskDetailsUseCase,
    val restoreTaskUseCase: RestoreTaskUseCase,
    val updateTaskUseCase: UpdateTaskUseCase,
)
