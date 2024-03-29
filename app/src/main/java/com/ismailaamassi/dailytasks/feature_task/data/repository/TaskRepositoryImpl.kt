package com.ismailaamassi.dailytasks.feature_task.data.repository

import android.content.SharedPreferences
import com.google.gson.Gson
import com.ismailaamassi.dailytasks.R
import com.ismailaamassi.dailytasks.core.domain.repository.SessionManagerRepository
import com.ismailaamassi.dailytasks.core.util.Resource
import com.ismailaamassi.dailytasks.core.util.SimpleResource
import com.ismailaamassi.dailytasks.core.util.UiText
import com.ismailaamassi.dailytasks.feature_task.data.local.TaskData
import com.ismailaamassi.dailytasks.feature_task.data.remote.TaskApi
import com.ismailaamassi.dailytasks.feature_task.data.remote.request.CheckTaskRequest
import com.ismailaamassi.dailytasks.feature_task.data.remote.request.CreateTaskRequest
import com.ismailaamassi.dailytasks.feature_task.domain.repository.TaskRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepositoryImpl @Inject constructor(
    private val api: TaskApi,
    private val gson: Gson,
    private val sessionManagerRepository: SessionManagerRepository,
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
            val response = api.createTask(request)
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

    override suspend fun restoreTask(taskData: TaskData): SimpleResource {
        return try {
            val response = api.restoreTask(taskData)
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

    override suspend fun getTasks(page: Int, pageSize: Int): Resource<List<TaskData>> {
        return try {
            val posts = api.getTasks(
                page = page,
                pageSize = pageSize
            )
            Resource.Success(data = posts)
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

    override suspend fun checkTask(taskId: String): SimpleResource {
        val request = CheckTaskRequest(taskId, true)
        return try {
            val response = api.checkTask(request)
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

    override suspend fun uncheckTask(taskId: String): SimpleResource {
        val request = CheckTaskRequest(taskId, false)
        return try {
            val response = api.checkTask(request)
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

    override suspend fun deleteTask(taskId: String): Resource<TaskData> {
        return try {
            val response = api.deleteTask(taskId)
            if (response.successful) {
                Resource.Success(response.data)
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

    override suspend fun updateTask(
        taskData: TaskData
    ): SimpleResource {
        return try {
            val response = api.updateTask(taskData)
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

    override suspend fun getTask(taskId: String): Resource<TaskData> {
        return try {
            val response = api.getTask(taskId)
            if (response.successful) {
                Resource.Success(response.data)
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