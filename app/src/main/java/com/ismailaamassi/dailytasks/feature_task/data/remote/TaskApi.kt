package com.ismailaamassi.dailytasks.feature_task.data.remote

import com.ismailaamassi.dailytasks.core.data.dto.BasicApiResponse
import com.ismailaamassi.dailytasks.core.util.Constants
import com.ismailaamassi.dailytasks.feature_task.data.local.TaskData
import com.ismailaamassi.dailytasks.feature_task.data.remote.request.CheckTaskRequest
import com.ismailaamassi.dailytasks.feature_task.data.remote.request.CreateTaskRequest
import com.ismailaamassi.dailytasks.feature_task.data.remote.request.UpdateTaskRequest
import retrofit2.http.*

interface TaskApi {

    @POST("${Constants.API_KEY}/${Constants.API_VERSION}/tasks")
    suspend fun createTask(
        @Body request: CreateTaskRequest
    ): BasicApiResponse<Unit>

    @POST("${Constants.API_KEY}/${Constants.API_VERSION}/tasks/restore")
    suspend fun restoreTask(
        @Body taskData: TaskData
    ): BasicApiResponse<Unit>

    @GET("${Constants.API_KEY}/${Constants.API_VERSION}/tasks")
    suspend fun getTasks(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): List<TaskData>

    @PUT("${Constants.API_KEY}/${Constants.API_VERSION}/tasks")
    suspend fun updateTask(
        @Body taskData: TaskData
    ): BasicApiResponse<Unit>

    @PUT("${Constants.API_KEY}/${Constants.API_VERSION}/tasks/check")
    suspend fun checkTask(
        @Body request: CheckTaskRequest
    ): BasicApiResponse<Unit>

    @GET("${Constants.API_KEY}/${Constants.API_VERSION}/tasks/{taskId}")
    suspend fun getTask(
        @Path("taskId") taskId: String
    ): BasicApiResponse<TaskData>

    @DELETE("${Constants.API_KEY}/${Constants.API_VERSION}/tasks/{taskId}")
    suspend fun deleteTask(
        @Path("taskId") taskId: String
    ): BasicApiResponse<TaskData>

}