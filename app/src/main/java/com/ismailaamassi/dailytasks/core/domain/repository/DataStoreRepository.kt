package com.ismailaamassi.dailytasks.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun saveOnBoardingState(completed: Boolean)
    suspend fun readOnBoardingState(): Flow<Boolean>

    suspend fun saveLoggedUserId(userId: String)
    suspend fun readLoggedUserId(): Flow<String>

    suspend fun saveLoggedUserToken(token: String)
    suspend fun readLoggedUserToken(): Flow<String>
}