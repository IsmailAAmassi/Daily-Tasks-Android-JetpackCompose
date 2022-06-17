package com.ismailaamassi.dailytasks.feature_task.presentation.task_list.compnenets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ismailaamassi.dailytasks.R
import com.ismailaamassi.dailytasks.feature_task.data.local.TaskData
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox


@Composable
fun TaskItem(
    taskData: TaskData,
    onCheckedChange: (String, Boolean) -> Unit = { _, _ ->

    },
    onDelete: (String) -> Unit = {
    },
    onUpdate: (String) -> Unit = {}
) {
    var checkedState by remember { mutableStateOf(taskData.status) }

    val delete = SwipeAction(
        icon = painterResource(id = R.drawable.ic_baseline_delete_sweep_24),
        background = Color.Red,
        isUndo = true,
        onSwipe = { /*onDelete(taskData.id)*/ }
    )

    val edit = SwipeAction(
        icon = painterResource(id = R.drawable.ic_baseline_edit_24),
        isUndo = false,
        background = Color.Blue,
        onSwipe = { /*onUpdate(taskData.id)*/ },
    )
    SwipeableActionsBox(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxWidth()
            .padding(4.dp)
            .clip(MaterialTheme.shapes.small),
        startActions = listOf(delete),
        endActions = listOf(edit)
    ) {
        Card(
            shape = MaterialTheme.shapes.small,
            elevation = 2.dp,
            backgroundColor = Color.White
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        CircleCheckbox(
                            modifier = Modifier.clip(CircleShape),
                            selected = checkedState,
                            onChecked = { checkedState = !checkedState },
                        )
                        Text(text = taskData.title)
                    }
                    Text(
                        modifier = Modifier
                            .padding(end = 16.dp, top = 4.dp),
                        text = taskData.category,
                    )
                }

                Text(
                    modifier = Modifier.padding(
                        end = 16.dp,
                        start = 12.dp,
                        bottom = 8.dp
                    ),
                    text = taskData.description,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

            }
        }
    }

}