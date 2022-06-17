package com.ismailaamassi.dailytasks.feature_task.presentation.task_list.compnenets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ismailaamassi.dailytasks.R
import com.ismailaamassi.dailytasks.core.presentation.ui.theme.CheckedColor
import com.ismailaamassi.dailytasks.core.presentation.ui.theme.SpaceSmall
import com.ismailaamassi.dailytasks.feature_task.data.local.TaskData
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox


@Composable
fun TaskItem(
    taskData: TaskData,
    onCheckedChange: () -> Unit,
    onDelete: (String) -> Unit = {
    },
    onUpdate: (String) -> Unit = {}
) {
    val checkedState = taskData.status

    val deleteSwipeAction = SwipeAction(
        icon = painterResource(id = R.drawable.ic_baseline_delete_sweep_24),
        background = Color.Red,
        isUndo = true,
        onSwipe = { /*onDelete(taskData.id)*/ }
    )

    val editSwipeAction = SwipeAction(
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
        startActions = listOf(deleteSwipeAction),
        endActions = listOf(editSwipeAction)
    ) {
        Card(
            shape = MaterialTheme.shapes.small,
            elevation = 2.dp,
            backgroundColor = Color.White
        ) {
            Row(
                verticalAlignment = Alignment.Top
            ) {
                CircleCheckbox(
                    modifier = Modifier.clip(CircleShape),
                    checked = checkedState,
                    onChecked = {
                        onCheckedChange()
                    },
                )
                Column {
                    Row(
                        modifier = Modifier
                            .padding(SpaceSmall.div(2))
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier
                                .weight(0.75f),
                            text = taskData.title,
                            fontSize = 18.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.SemiBold,
                            color = if (checkedState) CheckedColor else MaterialTheme.typography.body1.color
                        )
                        CategoryLabel(category = taskData.category, priority = taskData.priority)
                    }
                    Text(
                        modifier = Modifier.padding(
                            end = 16.dp,
                            bottom = 8.dp
                        ),
                        text = taskData.description,
                        maxLines = 2,
                        fontWeight = FontWeight.Light,
                        overflow = TextOverflow.Ellipsis,
                        color = if (checkedState) CheckedColor else MaterialTheme.typography.body1.color
                    )
                }
            }
        }
    }

}

@Composable
fun CategoryLabel(category: String, priority: Int) {
    val catColor = when (priority) {
        1 -> Color.Green
        2 -> Color.Cyan
        3 -> Color.Red
        else -> Color.Blue
    }
    Text(
        modifier = Modifier
            .border(width = 1.dp, color = catColor, shape = CircleShape)
            .padding(horizontal = 8.dp, vertical = 2.dp),
        text = category,
        fontSize = MaterialTheme.typography.body2.fontSize,
        color = catColor
    )
}