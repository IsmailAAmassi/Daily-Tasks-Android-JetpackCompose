package com.ismailaamassi.dailytasks.core.presentation.util

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ismailaamassi.dailytasks.core.util.UiText

@Composable
fun UiText.asString(): String {
    return when(this) {
        is UiText.DynamicString -> this.value
        is UiText.StringResource -> stringResource(id = this.id)
    }
}

fun UiText.asString(context: Context): String {
    return when(this) {
        is UiText.DynamicString -> this.value
        is UiText.StringResource -> context.getString(this.id)
    }
}

fun UiText.asToast(context: Context){
    Toast.makeText(context, asString(context), Toast.LENGTH_SHORT).show()
}