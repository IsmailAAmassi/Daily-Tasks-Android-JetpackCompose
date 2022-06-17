package com.ismailaamassi.dailytasks.feature_auth.domain.use_case

import com.ismailaamassi.dailytasks.core.domain.util.ValidationUtil
import com.ismailaamassi.dailytasks.feature_auth.domain.model.RegisterResult
import com.ismailaamassi.dailytasks.feature_auth.domain.repository.AuthRepository
import timber.log.Timber

class RegisterUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        username: String,
        email: String,
        password: String
    ): RegisterResult {

        val usernameError = ValidationUtil.validateUsername(email)
        val emailError = ValidationUtil.validateEmail(email)
        val passwordError = ValidationUtil.validatePassword(password)


        if (usernameError != null || emailError != null || passwordError != null) {
            val registerResult =  RegisterResult(
                usernameError = usernameError,
                emailError = emailError,
                passwordError = passwordError
            )
            Timber.tag("RegisterUseCase").d("invoke : registerResult $registerResult")
            return registerResult
        }

        val registerResult = authRepository.register(
            username = username,
            email = email,
            password = password
        )
        return RegisterResult(result = registerResult)
    }

}
