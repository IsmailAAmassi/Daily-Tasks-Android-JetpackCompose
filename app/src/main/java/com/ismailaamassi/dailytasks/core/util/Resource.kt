package com.ismailaamassi.dailytasks.core.util

typealias SimpleResource = Resource<Unit>


sealed class Resource<T>(val data: T? = null, val message: UiText? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(uiText: UiText, data: T? = null): Resource<T>(data, uiText)
//    class Loading<T>(val isLoading: Boolean = true): Resource<T>(null)
}