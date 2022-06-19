package com.ismailaamassi.dailytasks.feature_auth.domain.use_case

import com.ismailaamassi.dailytasks.core.domain.repository.SessionManagerRepository

class ReadOnBoardingUseCase(
    private val sessionManagerRepository: SessionManagerRepository
) {
    suspend operator fun invoke() =
        sessionManagerRepository.readOnBoardingState()

}