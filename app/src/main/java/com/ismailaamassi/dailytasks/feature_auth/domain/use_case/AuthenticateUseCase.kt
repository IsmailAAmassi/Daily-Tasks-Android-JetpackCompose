package com.ismailaamassi.dailytasks.feature_auth.domain.use_case

import com.ismailaamassi.dailytasks.core.util.SimpleResource
import com.ismailaamassi.dailytasks.feature_auth.domain.repository.AuthRepository


class AuthenticateUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): SimpleResource {
        return authRepository.authenticate()
    }
}