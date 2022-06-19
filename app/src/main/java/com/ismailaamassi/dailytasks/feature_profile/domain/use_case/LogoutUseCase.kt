package com.ismailaamassi.dailytasks.feature_profile.domain.use_case

import android.content.SharedPreferences
import com.ismailaamassi.dailytasks.core.domain.repository.SessionManagerRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogoutUseCase @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val sessionManagerRepository: SessionManagerRepository
) {
    suspend operator fun invoke() {
        sharedPreferences.edit().clear().apply()
        sessionManagerRepository.clearData()
    }
}