package com.ismailaamassi.dailytasks.feature_profile.domain.use_case

import com.ismailaamassi.dailytasks.core.domain.repository.SessionManagerRepository
import kotlinx.coroutines.flow.Flow

class ProfileDetailsUseCase(
    private val sessionManagerRepository: SessionManagerRepository
) {
    suspend operator fun invoke(): Flow<String> {
        return sessionManagerRepository.readLoggedUserName()
    }
}