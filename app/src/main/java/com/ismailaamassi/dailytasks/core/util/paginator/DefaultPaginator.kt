package com.ismailaamassi.dailytasks.core.util.paginator

import com.ismailaamassi.dailytasks.core.util.Resource
import com.ismailaamassi.dailytasks.core.util.UiText
import timber.log.Timber

class DefaultPaginator<T>(
    private val onLoadUpdated: (Boolean) -> Unit,
    private val onRequest: suspend (nextPage: Int) -> Resource<List<T>>,
    private val onError: suspend (UiText) -> Unit,
    private val onSuccess: (items: List<T>) -> Unit
): Paginator<T> {

    private var page = 0

    override suspend fun loadNextItems() {
        onLoadUpdated(true)
        when(val result = onRequest(page)) {
            is Resource.Success -> {
                val items = result.data ?: emptyList()
                page++
                onSuccess(items)
                onLoadUpdated(false)
            }
            is Resource.Error -> {
                onError(result.uiText ?: UiText.unknownError())
                onLoadUpdated(false)
            }
        }
    }
}