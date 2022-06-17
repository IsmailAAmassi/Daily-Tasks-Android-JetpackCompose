package com.ismailaamassi.dailytasks.feature_task.data.repository

import android.content.SharedPreferences
import com.ismailaamassi.dailytasks.R
import com.ismailaamassi.dailytasks.core.domain.repository.DataStoreRepository
import com.ismailaamassi.dailytasks.core.util.Resource
import com.ismailaamassi.dailytasks.core.util.SimpleResource
import com.ismailaamassi.dailytasks.core.util.UiText
import com.ismailaamassi.dailytasks.feature_task.data.remote.TaskApi
import com.ismailaamassi.dailytasks.feature_task.data.remote.request.CreateTaskRequest
import com.ismailaamassi.dailytasks.feature_task.domain.repository.TaskRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepositoryImpl @Inject constructor(
    private val taskApi: TaskApi,
    private val dataStoreRepository: DataStoreRepository,
    private val sharedPreferences: SharedPreferences
) : TaskRepository {

    override suspend fun createTask(
        title: String,
        description: String,
        category: String,
        priority: Int,
        time: Long
    ): SimpleResource {
        val request = CreateTaskRequest(title, description, category, priority, time)
        return try {
            val response = taskApi.createTask(request)
            if (response.successful) {
                Resource.Success(Unit)
            } else {
                response.message?.let { msg ->
                    Resource.Error(uiText = UiText.DynamicString(msg))
                } ?: Resource.Error(uiText = UiText.StringResource(R.string.error_unknown))
            }
        } catch (e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch (e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_oops_something_went_wrong)
            )
        }
    }
}