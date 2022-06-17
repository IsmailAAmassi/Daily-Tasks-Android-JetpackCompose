package com.ismailaamassi.dailytasks.feature_task.presentation.create_task

sealed class CreateTaskEvent {
    data class EnteredTitle(val title: String) : CreateTaskEvent()
    data class EnteredDescription(val description: String) : CreateTaskEvent()
    data class EnteredCategory(val category: String) : CreateTaskEvent()
    data class EnteredPriority(val priority: Int) : CreateTaskEvent()
    data class EnteredTime(val time: Long) : CreateTaskEvent()

    object CreateTask : CreateTaskEvent()
}