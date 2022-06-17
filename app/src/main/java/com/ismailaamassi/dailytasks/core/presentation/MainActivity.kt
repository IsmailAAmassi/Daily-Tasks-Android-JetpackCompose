@file:OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)

package com.ismailaamassi.dailytasks.core.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.ismailaamassi.dailytasks.NavGraphs
import com.ismailaamassi.dailytasks.core.presentation.ui.theme.DailyTasksTheme
import com.ismailaamassi.dailytasks.destinations.CreateTaskScreenDestination
import com.ismailaamassi.dailytasks.destinations.LoginScreenDestination
import com.ismailaamassi.dailytasks.destinations.RegisterScreenDestination
import com.ismailaamassi.dailytasks.destinations.TaskListScreenDestination
import com.ismailaamassi.dailytasks.feature_auth.presentation.login.LoginScreen
import com.ismailaamassi.dailytasks.feature_auth.presentation.register.RegisterScreen
import com.ismailaamassi.dailytasks.feature_task.presentation.create_task.CreateTaskScreen
import com.ismailaamassi.dailytasks.feature_task.presentation.task_list.TaskListScreen
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyTasksTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val scaffoldState = rememberScaffoldState()
                    Scaffold(
                        scaffoldState = scaffoldState,
                    ) {
                        DestinationsNavHost(navGraph = NavGraphs.root) {
                            composable(LoginScreenDestination) {
                                LoginScreen(
                                    scaffoldState = scaffoldState,
                                    navigator = destinationsNavigator
                                )
                            }
                            composable(RegisterScreenDestination) {
                                RegisterScreen(
                                    scaffoldState = scaffoldState,
                                    navigator = destinationsNavigator
                                )
                            }
                            composable(TaskListScreenDestination) {
                                TaskListScreen(
                                    scaffoldState = scaffoldState,
                                    navigator = destinationsNavigator
                                )
                            }
                            composable(CreateTaskScreenDestination) {
                                CreateTaskScreen(
                                    scaffoldState = scaffoldState,
                                    navigator = destinationsNavigator,
                                    taskId = this.navArgs.taskId
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
