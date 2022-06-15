package com.ismailaamassi.dailytasks.core.util

sealed class OtherResource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?): OtherResource<T>(data)
    class Error<T>(message: String, data: T? = null): OtherResource<T>(data, message)
}