package com.ismailaamassi.dailytasks.feature_auth.domain.use_case

import com.ismailaamassi.dailytasks.core.domain.repository.SessionManagerRepository

class SaveOnBoardingUseCase(
    private val sessionManagerRepository: SessionManagerRepository
) {
    suspend operator fun invoke(completed: Boolean) {
        sessionManagerRepository.saveOnBoardingState(completed)
    }
}