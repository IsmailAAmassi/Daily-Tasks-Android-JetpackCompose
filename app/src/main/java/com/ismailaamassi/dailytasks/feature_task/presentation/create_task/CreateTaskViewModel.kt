package com.ismailaamassi.dailytasks.feature_task.presentation.create_task

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ismailaamassi.dailytasks.core.domain.states.StandardTextFieldState
import com.ismailaamassi.dailytasks.core.util.Resource
import com.ismailaamassi.dailytasks.core.util.UiEvent
import com.ismailaamassi.dailytasks.core.util.UiText
import com.ismailaamassi.dailytasks.feature_task.domain.use_case.CreateTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CreateTaskViewModel @Inject constructor(
    private val createTaskUseCase: CreateTaskUseCase,
) : ViewModel() {

    var titleState = mutableStateOf(StandardTextFieldState())
        private set
    var descriptionState = mutableStateOf(StandardTextFieldState())
        private set
    var categoryState = mutableStateOf(StandardTextFieldState())
        private set


    var createTaskState = mutableStateOf(CreateTaskState(isLoading = false))

    var eventFlow = MutableSharedFlow<UiEvent>()
        private set

    fun onEvent(event: CreateTaskEvent) {
        when (event) {
            is CreateTaskEvent.EnteredTitle -> {
                titleState.value = titleState.value.copy(text = event.title)
            }
            is CreateTaskEvent.EnteredDescription -> {
                descriptionState.value = descriptionState.value.copy(text = event.description)
            }
            is CreateTaskEvent.EnteredCategory -> {
                categoryState.value = categoryState.value.copy(text = event.category)
            }
            is CreateTaskEvent.EnteredPriority -> Unit
            is CreateTaskEvent.EnteredTime -> Unit
            is CreateTaskEvent.CreateTask -> {
                onCreateTask()
            }
        }
    }

    private fun onCreateTask() {
        viewModelScope.launch {
            titleState.value = titleState.value.copy(error = null)
            descriptionState.value = descriptionState.value.copy(error = null)
            categoryState.value = categoryState.value.copy(error = null)

            createTaskState.value = createTaskState.value.copy(isLoading = true)
            val createTaskResult = createTaskUseCase(
                title = titleState.value.text,
                description = descriptionState.value.text,
                category = categoryState.value.text,
                priority = 1,
                time = -1,
            )

            if (createTaskResult.titleError != null) {
                titleState.value = titleState.value.copy(error = createTaskResult.titleError)
            }

            if (createTaskResult.categoryError != null) {
                categoryState.value =
                    categoryState.value.copy(error = createTaskResult.categoryError)
            }

            if (createTaskResult.descriptionError != null) {
                descriptionState.value =
                    descriptionState.value.copy(error = createTaskResult.descriptionError)
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
                                createTaskResult.result.message ?: UiText.unknownError()
                            )
                        )
                    }
                }
            }

            createTaskState.value = createTaskState.value.copy(isLoading = false)
        }
    }
}