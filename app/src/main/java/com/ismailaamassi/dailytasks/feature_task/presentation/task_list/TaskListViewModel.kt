package com.ismailaamassi.dailytasks.feature_task.presentation.task_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ismailaamassi.dailytasks.core.presentation.PagingState
import com.ismailaamassi.dailytasks.core.util.Event
import com.ismailaamassi.dailytasks.core.util.UiEvent
import com.ismailaamassi.dailytasks.core.util.paginator.DefaultPaginator
import com.ismailaamassi.dailytasks.core.util.task_checker.TaskChecker
import com.ismailaamassi.dailytasks.feature_task.data.local.TaskData
import com.ismailaamassi.dailytasks.feature_task.domain.use_case.ChangeCheckTaskUseCase
import com.ismailaamassi.dailytasks.feature_task.domain.use_case.GetTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
    private val changeCheckTaskUseCase: ChangeCheckTaskUseCase,
    private val taskChecker: TaskChecker
) : ViewModel() {


    var eventFlow = MutableSharedFlow<Event>()
        private set
    var pagingState by mutableStateOf<PagingState<TaskData>>(PagingState())
        private set

    private val paginator = DefaultPaginator(
        onLoadUpdated = { isLoading ->
            pagingState = pagingState.copy(
                isLoading = isLoading
            )
        },
        onRequest = { page ->
            getTasksUseCase(page = page)
        },
        onSuccess = { posts ->
            pagingState = pagingState.copy(
                items = pagingState.items + posts,
                endReached = posts.isEmpty(),
                isLoading = false
            )
        },
        onError = { uiText ->
            eventFlow.emit(UiEvent.ShowSnackbar(uiText))
        }
    )

    init {
        Timber.tag("TaskListViewModel").d("init : loadNextTodos")
        loadNextTodos()
    }

    fun loadNextTodos() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    fun onEvent(event: TaskListEvent) {
        when (event) {
            is TaskListEvent.NavCreateTask -> onNavCreateTask()
            is TaskListEvent.TaskCheckedChange -> {
                toggleTaskChecked(event.taskId)
            }
            is TaskListEvent.TaskDelete -> {

            }
            is TaskListEvent.TaskUpdate -> {

            }
        }
    }

    private fun toggleTaskChecked(taskId: String) {
        viewModelScope.launch {
            taskChecker.toggleCheck(
                tasks = pagingState.items,
                taskId = taskId,
                onRequest = { isChecked ->
                    changeCheckTaskUseCase(
                        taskId = taskId,
                        isChecked = isChecked,
                    )
                },
                onStateUpdated = { posts ->
                    Timber.tag("TaskListViewModel")
                        .d("toggleTaskChecked : onStateUpdated posts $posts")
                    pagingState = pagingState.copy(
                        items = posts,
                    )
                }
            )
        }
    }

    private fun onNavCreateTask() {

    }
}