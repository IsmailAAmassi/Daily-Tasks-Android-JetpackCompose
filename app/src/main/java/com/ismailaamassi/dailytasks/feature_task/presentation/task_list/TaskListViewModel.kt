package com.ismailaamassi.dailytasks.feature_task.presentation.task_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ismailaamassi.dailytasks.R
import com.ismailaamassi.dailytasks.core.presentation.PagingState
import com.ismailaamassi.dailytasks.core.util.Event
import com.ismailaamassi.dailytasks.core.util.Resource
import com.ismailaamassi.dailytasks.core.util.UiEvent
import com.ismailaamassi.dailytasks.core.util.UiText
import com.ismailaamassi.dailytasks.core.util.paginator.DefaultPaginator
import com.ismailaamassi.dailytasks.core.util.task_checker.TaskChecker
import com.ismailaamassi.dailytasks.feature_task.data.local.TaskData
import com.ismailaamassi.dailytasks.feature_task.domain.use_case.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    private val taskChecker: TaskChecker,
) : ViewModel() {


    var eventFlow = MutableSharedFlow<Event>()
        private set
    var pagingState by mutableStateOf<PagingState<TaskData>>(PagingState())
        private set

    var state by mutableStateOf(TaskListState())
        private set

    private var recentlyDeletedTask: TaskData? = null

    private val paginator = DefaultPaginator(
        onLoadUpdated = { isLoading ->
            pagingState = pagingState.copy(
                isLoading = isLoading
            )
        },
        onRequest = { page ->
            taskUseCases.getTasksUseCase(page = page)
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
                deleteTask(event.taskId)
            }
            is TaskListEvent.TaskUpdate -> {

            }
            is TaskListEvent.TaskRestore -> {
                restoreRecentlyDeletedTask()
            }
        }
    }

    private fun restoreRecentlyDeletedTask() {
        viewModelScope.launch {
            recentlyDeletedTask?.let {
                when(val result = taskUseCases.restoreTaskUseCase(it)){
                    is Resource.Success -> {
                        // TODO: Insert it to list before null it
                        recentlyDeletedTask = null
                    }
                    is Resource.Error -> {
                        eventFlow.emit(
                            UiEvent.ShowSnackbar(result.uiText ?: UiText.unknownError())
                        )
                    }
                }
            }
        }
    }

    private fun deleteTask(taskId: String) {
        viewModelScope.launch {
            when (val result = taskUseCases.deleteTaskUseCase(taskId)) {
                is Resource.Success -> {
                    pagingState = pagingState.copy(
                        items = pagingState.items.filter {
                            it.id != taskId
                        }
                    )
                    recentlyDeletedTask = result.data
                    eventFlow.emit(
                        UiEvent.ShowSnackbar(
                            UiText.StringResource(
                                R.string.successfully_deleted_post
                            ),
                            action = "Undo"
                        )
                    )
                }
                is Resource.Error -> {
                    eventFlow.emit(
                        UiEvent.ShowSnackbar(result.uiText ?: UiText.unknownError())
                    )
                }
            }
        }
    }

    private fun toggleTaskChecked(taskId: String) {
        viewModelScope.launch {
            taskChecker.toggleCheck(
                tasks = pagingState.items,
                taskId = taskId,
                onRequest = { isChecked ->
                    taskUseCases.changeCheckTaskUseCase(
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