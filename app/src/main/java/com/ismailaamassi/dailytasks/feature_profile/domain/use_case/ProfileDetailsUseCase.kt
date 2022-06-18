package com.ismailaamassi.dailytasks.feature_profile.domain.use_case

import com.ismailaamassi.dailytasks.core.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class ProfileDetailsUseCase(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(): Flow<String> {
        return dataStoreRepository.readLoggedUserName()
    }
}