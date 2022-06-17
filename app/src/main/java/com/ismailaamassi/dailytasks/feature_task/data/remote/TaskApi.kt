package com.ismailaamassi.dailytasks.feature_task.data.remote

import com.ismailaamassi.dailytasks.core.data.dto.BasicApiResponse
import com.ismailaamassi.dailytasks.feature_task.data.remote.request.CreateTaskRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface TaskApi {

    @POST("tasks")
    suspend fun createTask(
        @Body request: CreateTaskRequest
    ): BasicApiResponse<Unit>
}