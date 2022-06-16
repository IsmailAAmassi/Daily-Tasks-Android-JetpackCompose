package com.ismailaamassi.dailytasks.core.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ismailaamassi.dailytasks.core.presentation.ui.theme.Shapes

@Composable
fun StandardPrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .then(modifier),
        onClick = onClick,
        shape = Shapes.medium,
    ) {
        Text(text = text, style = MaterialTheme.typography.body1)
    }
}