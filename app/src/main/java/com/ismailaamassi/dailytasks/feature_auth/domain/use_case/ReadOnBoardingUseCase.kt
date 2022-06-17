package com.ismailaamassi.dailytasks.feature_auth.domain.use_case

import com.ismailaamassi.dailytasks.core.domain.repository.DataStoreRepository

class ReadOnBoardingUseCase(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke() =
        dataStoreRepository.readOnBoardingState()

}