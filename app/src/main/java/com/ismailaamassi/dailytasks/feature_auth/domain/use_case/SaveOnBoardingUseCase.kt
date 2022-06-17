package com.ismailaamassi.dailytasks.feature_auth.domain.use_case

import com.ismailaamassi.dailytasks.core.domain.repository.DataStoreRepository
import com.ismailaamassi.dailytasks.feature_auth.domain.repository.AuthRepository

class SaveOnBoardingUseCase(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(completed: Boolean) {
        dataStoreRepository.saveOnBoardingState(completed)
    }
}