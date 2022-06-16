package com.ismailaamassi.dailytasks.feature_auth.data.remote

import com.ismailaamassi.dailytasks.core.data.dto.BasicApiResponse
import com.ismailaamassi.dailytasks.feature_auth.data.remote.request.LoginRequest
import com.ismailaamassi.dailytasks.feature_auth.data.remote.response.AuthResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): BasicApiResponse<AuthResponse>


}