package com.ismailaamassi.dailytasks.feature_task.data.remote

import com.ismailaamassi.dailytasks.core.data.dto.BasicApiResponse
import com.ismailaamassi.dailytasks.feature_task.data.local.TaskData
import com.ismailaamassi.dailytasks.feature_task.data.remote.request.CreateTaskRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TaskApi {

    @POST("tasks")
    suspend fun createTask(
        @Body request: CreateTaskRequest
    ): BasicApiResponse<Unit>

    @GET("tasks")
    suspend fun getTasks(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): BasicApiResponse<List<TaskData>>
}