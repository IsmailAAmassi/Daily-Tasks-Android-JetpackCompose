package com.ismailaamassi.dailytasks.feature_task.presentation.task_list.compnenets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ismailaamassi.dailytasks.core.presentation.ui.theme.CheckedColor

@Composable
fun CircleCheckbox(
    modifier: Modifier = Modifier,
    checked: Boolean,
    enabled: Boolean = true,
    onChecked: () -> Unit
) {

    val color = MaterialTheme.colors
    val imageVector = if (checked) Icons.Filled.CheckCircle else Icons.Outlined.Circle
    val tint = if (checked) CheckedColor else Color.LightGray.copy(alpha = 0.8f)
    val background = if (checked) Color.White else Color.Transparent

    IconButton(
        modifier = Modifier
            .offset(x = 4.dp, y = 4.dp)
            .then(modifier),
        onClick = { onChecked() },
        enabled = enabled
    ) {

        Icon(
            imageVector = imageVector, tint = tint,
            modifier = Modifier.background(background, shape = CircleShape),
            contentDescription = "checkbox"
        )
    }
}