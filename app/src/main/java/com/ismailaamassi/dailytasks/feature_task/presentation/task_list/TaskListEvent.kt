package com.ismailaamassi.dailytasks.feature_task.presentation.task_list

sealed class TaskListEvent {
    data class TaskUpdate(val taskId: String) : TaskListEvent()
    data class TaskDelete(val taskId: String) : TaskListEvent()
    data class TaskCheckedChange(val taskId: String) : TaskListEvent()
    object TaskRestore : TaskListEvent()
    object NavCreateTask : TaskListEvent()
}