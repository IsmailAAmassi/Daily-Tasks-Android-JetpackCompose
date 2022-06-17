package com.ismailaamassi.dailytasks.feature_task.presentation.util

import com.ismailaamassi.dailytasks.core.util.Error

sealed class TaskError : Error() {
    object FieldEmpty : TaskError()
    object InputTooShort : TaskError()
    object InvalidTime : TaskError()
}
