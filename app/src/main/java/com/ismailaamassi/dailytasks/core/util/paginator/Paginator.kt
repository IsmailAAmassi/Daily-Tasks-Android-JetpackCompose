package com.ismailaamassi.dailytasks.core.util.paginator

interface Paginator<T> {

    suspend fun loadNextItems()
}