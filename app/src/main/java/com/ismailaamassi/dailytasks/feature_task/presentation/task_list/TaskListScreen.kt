package com.ismailaamassi.dailytasks.feature_task.presentation.task_list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ismailaamassi.dailytasks.core.presentation.components.StandardLoadingAnimation
import com.ismailaamassi.dailytasks.core.presentation.ui.theme.SpaceLarge
import com.ismailaamassi.dailytasks.core.presentation.ui.theme.SpaceSmall
import com.ismailaamassi.dailytasks.core.presentation.util.asString
import com.ismailaamassi.dailytasks.core.util.UiEvent
import com.ismailaamassi.dailytasks.destinations.CreateTaskScreenDestination
import com.ismailaamassi.dailytasks.destinations.LoginScreenDestination
import com.ismailaamassi.dailytasks.feature_task.presentation.task_list.compnenets.ProfileSection
import com.ismailaamassi.dailytasks.feature_task.presentation.task_list.compnenets.TaskItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.Direction
import kotlinx.coroutines.flow.collectLatest

//@Inject lateinit var faker: Faker

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun TaskListScreen(
    scaffoldState: ScaffoldState,
    navigator: DestinationsNavigator,
    viewModel: TaskListViewModel = hiltViewModel(),
    onNavigate: (Direction) -> Unit = { navigator.navigate(it) }
) {

    val taskListState = viewModel.state
    val pagingState = viewModel.pagingState
    val context = LocalContext.current


    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { uiEvent ->
            when (uiEvent) {
                is UiEvent.Navigate -> onNavigate(uiEvent.destination)
                is UiEvent.ShowSnackbar -> {
                    val actionClickResult = scaffoldState.snackbarHostState.showSnackbar(
                        message = uiEvent.uiText.asString(context),
                        actionLabel = uiEvent.action
                    )
                    if (actionClickResult == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(TaskListEvent.TaskRestore)
                    }
                }
                is UiEvent.ShowToast -> Unit
                else -> Unit
            }
        }

    }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(text = {
                Row {
                    Icon(
                        imageVector = Icons.Default.Add,
                        tint = Color.White,
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "New Task", color = Color.White)
                }
            }, onClick = {
                onNavigate(CreateTaskScreenDestination())
            })
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = SpaceLarge),
            ) {
                Column {
                    ProfileSection(name = taskListState.username, onClickLogout = {
                        viewModel.onEvent(TaskListEvent.Logout)
                        onNavigate(LoginScreenDestination)
                    })
                    LazyColumn(modifier = Modifier.padding(SpaceSmall)) {
                        items(pagingState.items.size) { i ->
                            val currentTask = pagingState.items[i]
                            if (i >= pagingState.items.size - 1 && !pagingState.endReached && !pagingState.isLoading) {
                                viewModel.loadNextTodos()
                            }
                            TaskItem(
                                taskData = currentTask,
                                onCheckedChange = {
                                    viewModel.onEvent(TaskListEvent.TaskCheckedChange(currentTask.id))
                                },
                                onDelete = {
                                    viewModel.onEvent(TaskListEvent.TaskDelete(currentTask.id))
                                },
                                onUpdate = {
                                    onNavigate(CreateTaskScreenDestination(taskId = currentTask.id))
                                }
                            )
                        }
                        item {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Spacer(modifier = Modifier.height(SpaceLarge.times(3)))
                                if (pagingState.isLoading) {
                                    StandardLoadingAnimation()
                                }
                            }
                        }
                    }


                }
            }
        }
    }
}