package com.ismailaamassi.dailytasks.feature_auth.domain.repository

import com.ismailaamassi.dailytasks.core.util.SimpleResource

interface AuthRepository {

    suspend fun register(username: String, email: String, password: String): SimpleResource

    suspend fun login(email: String, password: String): SimpleResource

    suspend fun authenticate(): SimpleResource
}