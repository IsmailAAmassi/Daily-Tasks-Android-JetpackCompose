package com.ismailaamassi.dailytasks.feature_task.presentation.task_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ismailaamassi.dailytasks.core.presentation.PagingState
import com.ismailaamassi.dailytasks.core.util.UiEvent
import com.ismailaamassi.dailytasks.core.util.paginator.DefaultPaginator
import com.ismailaamassi.dailytasks.feature_task.data.local.TaskData
import com.ismailaamassi.dailytasks.feature_task.domain.use_case.GetTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase
) : ViewModel() {


    var eventFlow = MutableSharedFlow<UiEvent>()
        private set

    var pagingState = mutableStateOf<PagingState<TaskData>>(PagingState())
        private set

    private val paginator = DefaultPaginator(
        onLoadUpdated = { isLoading ->
            pagingState.value = pagingState.value.copy(
                isLoading = isLoading
            )
        },
        onRequest = { page ->
            getTasksUseCase(page = page)
        },
        onSuccess = { posts ->
            pagingState.value = pagingState.value.copy(
                items = pagingState.value.items + posts,
                endReached = posts.isEmpty(),
                isLoading = false
            )
        },
        onError = { uiText ->
            eventFlow.emit(UiEvent.ShowSnackbar(uiText))
        }
    )

    init {
        loadNextTodos()
    }

    fun loadNextTodos() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    fun onEvent(event: TaskListEvent) {
        when (event) {
            TaskListEvent.NavCreateTask -> onNavCreateTask()
        }
    }

    private fun onNavCreateTask() {

    }
}