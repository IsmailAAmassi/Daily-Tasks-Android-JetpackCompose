package com.ismailaamassi.dailytasks.feature_task.presentation.task_list.compnenets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ismailaamassi.dailytasks.core.presentation.ui.theme.Shapes
import com.ismailaamassi.dailytasks.core.presentation.ui.theme.SpaceLarge
import com.ismailaamassi.dailytasks.core.presentation.ui.theme.SpaceMedium


@Preview
@Composable
fun ProfileSection(
    name: String = ""
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = Color.LightGray.copy(alpha = 0.5f),
        shape = Shapes.large.copy(topStart = CornerSize(0), topEnd = CornerSize(0.dp))
    ) {
        Column {
            Spacer(modifier = Modifier.width(SpaceLarge))
            Spacer(modifier = Modifier.width(SpaceLarge))
            Spacer(modifier = Modifier.width(SpaceLarge))

            Row(
                modifier = Modifier.padding(SpaceMedium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .size(52.dp),
                        tint = Color.White,
                        imageVector = Icons.Default.Person,
                        contentDescription = "User Image"
                    )
                }
                Spacer(modifier = Modifier.width(SpaceMedium))
                Column {
                    Text(text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontSize = MaterialTheme.typography.h2.fontSize,
                            )
                        ) {
                            append("Hello, ")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontSize = MaterialTheme.typography.h2.fontSize,
                                fontWeight = MaterialTheme.typography.h2.fontWeight
                            )
                        ) {
                            append(name)
                        }
                    })
                    Text(
                        text = "Have a nice day!",
                        fontSize = MaterialTheme.typography.body1.fontSize
                    )
                }
            }

            Spacer(modifier = Modifier.width(SpaceLarge))
            Spacer(modifier = Modifier.width(SpaceLarge))
            Spacer(modifier = Modifier.width(SpaceLarge))

        }
    }
}