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
import com.ismailaamassi.dailytasks.core.presentation.ui.theme.SpaceLarge
import com.ismailaamassi.dailytasks.core.presentation.ui.theme.SpaceSmall
import com.ismailaamassi.dailytasks.core.presentation.util.asString
import com.ismailaamassi.dailytasks.core.util.UiEvent
import com.ismailaamassi.dailytasks.destinations.CreateTaskScreenDestination
import com.ismailaamassi.dailytasks.destinations.DirectionDestination
import com.ismailaamassi.dailytasks.feature_task.data.local.TaskData
import com.ismailaamassi.dailytasks.feature_task.presentation.task_list.compnenets.ProfileSection
import com.ismailaamassi.dailytasks.feature_task.presentation.task_list.compnenets.TaskItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest

//@Inject lateinit var faker: Faker

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun TaskListScreen(
    scaffoldState: ScaffoldState,
    navigator: DestinationsNavigator,
    viewModel: TaskListViewModel = hiltViewModel(),
    onNavigate: (DirectionDestination) -> Unit = { navigator.navigate(it) }
) {

    val pagingState = viewModel.pagingState.value
    val context = LocalContext.current

    val fakeTasks = mutableListOf<TaskData>()
    repeat(10) { i ->
        fakeTasks.add(
            TaskData(
                title = "Title $i ",
                description = "Description $i, Description $i Description $i Description $i",
                category = "Category ${i % 3}",
                status = false,
                priority = 1,
                time = -1L,
                createdAt = -1L,
                updatedAt = -1L,
                profileId = "profileId 1",
                id = "id $i"
            )
        )
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { uiEvent ->
            when (uiEvent) {
                is UiEvent.Navigate -> onNavigate(uiEvent.destination)
                is UiEvent.ShowSnackbar -> scaffoldState.snackbarHostState.showSnackbar(
                    message = uiEvent.uiText.asString(context)
                )
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
                onNavigate(CreateTaskScreenDestination)
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
                    ProfileSection(name = "Coach")

                    LazyColumn(modifier = Modifier.padding(SpaceSmall)) {
                        items(fakeTasks.size) { i ->
//                            val currentTask = fakeTasks[i]
                            val currentTask = pagingState.items[i]
                            if (i >= pagingState.items.size - 1 && !pagingState.endReached && !pagingState.isLoading) {
                                viewModel.loadNextTodos()
                            }
                            TaskItem(taskData = currentTask)
                            if (i < pagingState.items.size - 1) {
                                Spacer(modifier = Modifier.height(SpaceLarge))
                            }
                        }
                        item {
                            Spacer(modifier = Modifier.height(SpaceLarge.times(3)))
                        }
                    }

                    if (pagingState.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }
    }
}