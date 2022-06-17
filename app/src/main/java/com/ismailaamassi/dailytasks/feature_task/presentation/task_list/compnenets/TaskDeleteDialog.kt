@file:OptIn(ExperimentalComposeUiApi::class)

package com.ismailaamassi.dailytasks.feature_task.presentation.task_list.compnenets

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi

@Composable
fun TaskDeleteDialog() {
    var openDialog by remember { mutableStateOf(false) }

    Button(onClick = {
        openDialog = true
    }) {
        Text("Click me")
    }
    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
            openDialog = false
        },
        title = {
            Text(text = "Dialog Title")
        },
        text = {
            Text("Here is a text ")
        },
        confirmButton = {
            Button(

                onClick = {
                    openDialog = false
                }) {
                Text("This is the Confirm Button")
            }
        },
        dismissButton = {
            Button(

                onClick = {
                    openDialog = false
                }) {
                Text("This is the dismiss Button")
            }
        }
    )
}