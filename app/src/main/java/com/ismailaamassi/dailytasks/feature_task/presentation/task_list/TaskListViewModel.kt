package com.ismailaamassi.dailytasks.feature_task.presentation.task_list

import androidx.lifecycle.ViewModel
import com.ismailaamassi.dailytasks.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor() : ViewModel() {


    var eventFlow = MutableSharedFlow<UiEvent>()
        private set

    init {
        loadNextTodos()
    }

    fun loadNextTodos(){

    }

    fun onEvent(event: TaskListEvent) {
        when (event) {
            TaskListEvent.NavCreateTask -> onNavCreateTask()
        }
    }

    private fun onNavCreateTask() {

    }
}