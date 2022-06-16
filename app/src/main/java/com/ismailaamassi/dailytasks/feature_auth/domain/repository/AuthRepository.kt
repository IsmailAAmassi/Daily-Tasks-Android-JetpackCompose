package com.ismailaamassi.dailytasks.feature_auth.domain.repository

import com.ismailaamassi.dailytasks.core.util.SimpleResource

interface AuthRepository {

    suspend fun login(email: String, password: String): SimpleResource
}