package com.ismailaamassi.dailytasks.feature_profile.domain.use_case

import android.content.SharedPreferences
import com.ismailaamassi.dailytasks.core.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogoutUseCase @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke() {
        sharedPreferences.edit().clear().apply()
        dataStoreRepository.clearData()
    }
}