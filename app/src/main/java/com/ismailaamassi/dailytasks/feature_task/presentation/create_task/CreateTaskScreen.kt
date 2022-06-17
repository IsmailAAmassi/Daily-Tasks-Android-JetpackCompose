package com.ismailaamassi.dailytasks.feature_task.presentation.create_task

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.ismailaamassi.dailytasks.R
import com.ismailaamassi.dailytasks.core.presentation.components.StandardLoadingAnimation
import com.ismailaamassi.dailytasks.core.presentation.components.StandardPrimaryButton
import com.ismailaamassi.dailytasks.core.presentation.components.StandardTextField
import com.ismailaamassi.dailytasks.core.presentation.ui.theme.SpaceLarge
import com.ismailaamassi.dailytasks.core.presentation.ui.theme.SpaceMedium
import com.ismailaamassi.dailytasks.core.presentation.util.asString
import com.ismailaamassi.dailytasks.core.util.UiEvent
import com.ismailaamassi.dailytasks.feature_task.presentation.util.TaskError
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@Destination
@Composable
fun CreateTaskScreen(
    scaffoldState: ScaffoldState,
    navigator: DestinationsNavigator,
    taskId: String = "",
    viewModel: CreateTaskViewModel = hiltViewModel(),
    onPopBackStack: () -> Unit = {
        navigator.popBackStack()
    },
) {
    val titleState = viewModel.titleState.value
    val descriptionState = viewModel.descriptionState.value
    val categoryState = viewModel.categoryState.value

    val state = viewModel.createTaskState.value
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        if (taskId.isNotEmpty()) viewModel.loadTaskData(taskId)
        viewModel.eventFlow.collectLatest { uiEvent ->
            when (uiEvent) {
                is UiEvent.PopBackStack -> onPopBackStack()
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = uiEvent.uiText.asString(context)
                    )
                }
                else -> Unit
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(SpaceLarge),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
        ) {
            Timber.tag("CreateTaskScreen.kt").d("JC:: CreateTaskScreen : taskId $taskId")

            val titleId = if (taskId.isEmpty()) {
                R.string.screen_create_task
            } else {
                R.string.screen_update_task
            }

            Text(
                text = stringResource(id = titleId),
                style = MaterialTheme.typography.h1
            )

            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = categoryState.text,
                hint = stringResource(id = R.string.hint_task_category),
                keyboardType = KeyboardType.Text,
                onValueChange = {
                    viewModel.onEvent(CreateTaskEvent.EnteredCategory(it))
                },
                error = when (categoryState.error) {
                    is TaskError.FieldEmpty -> stringResource(id = R.string.error_field_empty)
                    else -> ""
                },
            )

            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = titleState.text,
                hint = stringResource(id = R.string.hint_task_title),
                keyboardType = KeyboardType.Text,
                maxLines = 1,
                isLastField = false,
                onValueChange = {
                    viewModel.onEvent(CreateTaskEvent.EnteredTitle(it))
                },
                error = when (titleState.error) {
                    is TaskError.FieldEmpty -> stringResource(id = R.string.error_field_empty)
                    else -> ""
                },
            )



            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = descriptionState.text,
                hint = stringResource(id = R.string.hint_task_description),
                keyboardType = KeyboardType.Text,
                maxLines = 4,
                singleLine = false,
                onValueChange = {
                    viewModel.onEvent(CreateTaskEvent.EnteredDescription(it))
                },
            )


            Spacer(modifier = Modifier.height(SpaceLarge))
            StandardPrimaryButton(text = stringResource(id = R.string.btn_create)) {
                viewModel.onEvent(CreateTaskEvent.CreateTask)
            }

            Spacer(modifier = Modifier.height(SpaceLarge))

            if (state.isLoading) StandardLoadingAnimation(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}