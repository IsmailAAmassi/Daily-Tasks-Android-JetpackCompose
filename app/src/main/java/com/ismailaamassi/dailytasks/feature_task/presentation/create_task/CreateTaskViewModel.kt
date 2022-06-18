package com.ismailaamassi.dailytasks.feature_task.presentation.create_task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ismailaamassi.dailytasks.core.domain.states.StandardTextFieldState
import com.ismailaamassi.dailytasks.core.util.Resource
import com.ismailaamassi.dailytasks.core.util.UiEvent
import com.ismailaamassi.dailytasks.core.util.UiText
import com.ismailaamassi.dailytasks.feature_task.data.local.TaskData
import com.ismailaamassi.dailytasks.feature_task.domain.use_case.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CreateTaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
) : ViewModel() {

    var titleState by mutableStateOf(StandardTextFieldState())
        private set
    var descriptionState by mutableStateOf(StandardTextFieldState())
        private set
    var categoryState by mutableStateOf(StandardTextFieldState())
        private set

    private var currentTask : TaskData? = null

    var createTaskState = mutableStateOf(CreateTaskState(isLoading = false))

    var eventFlow = MutableSharedFlow<UiEvent>()
        private set

    fun onEvent(event: CreateTaskEvent) {
        when (event) {
            is CreateTaskEvent.EnteredTitle -> {
                titleState = titleState.copy(text = event.title)
            }
            is CreateTaskEvent.EnteredDescription -> {
                descriptionState = descriptionState.copy(text = event.description)
            }
            is CreateTaskEvent.EnteredCategory -> {
                categoryState = categoryState.copy(text = event.category)
            }
            is CreateTaskEvent.EnteredPriority -> Unit
            is CreateTaskEvent.EnteredTime -> Unit
            is CreateTaskEvent.CreateTask -> {
                createTask()
            }
            is CreateTaskEvent.UpdateTask -> {
                updateTask()
            }
        }
    }

    private fun createTask() {
        viewModelScope.launch {
            titleState = titleState.copy(error = null)
            descriptionState = descriptionState.copy(error = null)
            categoryState = categoryState.copy(error = null)

            createTaskState.value = createTaskState.value.copy(isLoading = true)
            val createTaskResult = taskUseCases.createTaskUseCase(
                title = titleState.text,
                description = descriptionState.text,
                category = categoryState.text,
                priority = 1,
                time = -1,
            )
            createTaskState.value = createTaskState.value.copy(isLoading = false)

            if (createTaskResult.titleError != null) {
                titleState = titleState.copy(error = createTaskResult.titleError)
            }

            if (createTaskResult.categoryError != null) {
                categoryState =
                    categoryState.copy(error = createTaskResult.categoryError)
            }

            if (createTaskResult.descriptionError != null) {
                descriptionState =
                    descriptionState.copy(error = createTaskResult.descriptionError)
            }

            if (createTaskResult.priorityError != null) {
                Timber.tag("CreateTaskViewModel").d("onCreateTask : Not Implemented Yet !!")
            }

            if (createTaskResult.timeError != null) {
                Timber.tag("CreateTaskViewModel").d("onCreateTask : Not Implemented Yet !!")
            }

            createTaskResult.result?.let {
                when (createTaskResult.result) {
                    is Resource.Success -> eventFlow.emit(UiEvent.PopBackStack)
                    is Resource.Error -> {
                        eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                createTaskResult.result.uiText ?: UiText.unknownError()
                            )
                        )
                    }
                }
            }

        }
    }

    private fun updateTask() {
        if (currentTask == null){
            return
        }
        viewModelScope.launch {
            titleState = titleState.copy(error = null)
            descriptionState = descriptionState.copy(error = null)
            categoryState = categoryState.copy(error = null)

            createTaskState.value = createTaskState.value.copy(isLoading = true)

            val createTaskResult = taskUseCases.updateTaskUseCase(
                currentTask!!.copy(
                    title = titleState.text,
                    description = descriptionState.text,
                    category = categoryState.text,
                )
            )
            createTaskState.value = createTaskState.value.copy(isLoading = false)

            if (createTaskResult.titleError != null) {
                titleState = titleState.copy(error = createTaskResult.titleError)
            }

            if (createTaskResult.categoryError != null) {
                categoryState =
                    categoryState.copy(error = createTaskResult.categoryError)
            }

            if (createTaskResult.descriptionError != null) {
                descriptionState =
                    descriptionState.copy(error = createTaskResult.descriptionError)
            }

            if (createTaskResult.priorityError != null) {
                Timber.tag("CreateTaskViewModel").d("onCreateTask : Not Implemented Yet !!")
            }

            if (createTaskResult.timeError != null) {
                Timber.tag("CreateTaskViewModel").d("onCreateTask : Not Implemented Yet !!")
            }

            createTaskResult.result?.let {
                when (createTaskResult.result) {
                    is Resource.Success -> eventFlow.emit(UiEvent.PopBackStack)
                    is Resource.Error -> {
                        eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                createTaskResult.result.uiText ?: UiText.unknownError()
                            )
                        )
                    }
                }
            }

        }
    }

    suspend fun loadTaskDetails(taskId: String) {
        Timber.tag("CreateTaskViewModel").d("loadTaskDetails : ")
        createTaskState.value = createTaskState.value.copy(isLoading = true)
        val result = taskUseCases.getTaskDetailsUseCase(taskId)
        createTaskState.value = createTaskState.value.copy(isLoading = false)
        Timber.tag("CreateTaskViewModel.kt").d("JC:: loadTaskDetails : result $result")
        when (result) {
            is Resource.Success -> {
                result.data?.let { task ->
                    currentTask = task
                    titleState = titleState.copy(text = task.title)
                    descriptionState = descriptionState.copy(text = task.description ?: "")
                    categoryState = categoryState.copy(text = task.category)
                }
            }
            is Resource.Error -> {
                eventFlow.emit(
                    UiEvent.ShowSnackbar(result.uiText ?: UiText.unknownError())
                )
            }
        }
    }
}