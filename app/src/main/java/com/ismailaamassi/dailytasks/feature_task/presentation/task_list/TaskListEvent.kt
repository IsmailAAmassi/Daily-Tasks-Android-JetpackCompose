package com.ismailaamassi.dailytasks.feature_task.presentation.task_list

sealed class TaskListEvent {
    object NavCreateTask : TaskListEvent()
}