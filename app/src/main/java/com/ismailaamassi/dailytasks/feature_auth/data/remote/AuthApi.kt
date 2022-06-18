package com.ismailaamassi.dailytasks.feature_auth.data.remote

import com.ismailaamassi.dailytasks.core.data.dto.BasicApiResponse
import com.ismailaamassi.dailytasks.core.util.Constants
import com.ismailaamassi.dailytasks.feature_auth.data.remote.request.LoginRequest
import com.ismailaamassi.dailytasks.feature_auth.data.remote.request.RegisterRequest
import com.ismailaamassi.dailytasks.feature_auth.data.remote.response.AuthResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {
    @POST("${Constants.API_KEY}/${Constants.API_VERSION}/auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): BasicApiResponse<Unit>

    @POST("${Constants.API_KEY}/${Constants.API_VERSION}/auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): BasicApiResponse<AuthResponse>

    @GET("${Constants.API_KEY}/${Constants.API_VERSION}/auth/authenticate")
    suspend fun authenticate()
}