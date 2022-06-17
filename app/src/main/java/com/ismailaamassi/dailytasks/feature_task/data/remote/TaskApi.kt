package com.ismailaamassi.dailytasks.feature_task.data.remote

import com.ismailaamassi.dailytasks.core.data.dto.BasicApiResponse
import com.ismailaamassi.dailytasks.feature_task.data.local.TaskData
import com.ismailaamassi.dailytasks.feature_task.data.remote.request.CheckTaskRequest
import com.ismailaamassi.dailytasks.feature_task.data.remote.request.CreateTaskRequest
import com.ismailaamassi.dailytasks.feature_task.data.remote.request.UpdateTaskRequest
import retrofit2.http.*

interface TaskApi {

    @POST("tasks")
    suspend fun createTask(
        @Body request: CreateTaskRequest
    ): BasicApiResponse<Unit>

    @POST("tasks/restore")
    suspend fun restoreTask(
        @Body taskData: TaskData
    ): BasicApiResponse<Unit>

    @GET("tasks")
    suspend fun getTasks(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): List<TaskData>

    @PUT("tasks")
    suspend fun updateTask(
        @Body request: UpdateTaskRequest
    ): BasicApiResponse<Unit>

    @PUT("tasks/check")
    suspend fun checkTask(
        @Body request: CheckTaskRequest
    ): BasicApiResponse<Unit>

    @POST("tasks/{taskId}")
    suspend fun getTask(
        @Path("taskId") taskId: String
    ): BasicApiResponse<Unit>

    @DELETE("tasks/{taskId}")
    suspend fun deleteTask(
        @Path("taskId") taskId: String
    ): BasicApiResponse<TaskData>

}