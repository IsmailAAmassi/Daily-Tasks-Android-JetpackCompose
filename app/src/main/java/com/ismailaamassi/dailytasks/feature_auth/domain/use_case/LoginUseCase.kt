package com.ismailaamassi.dailytasks.feature_auth.domain.use_case

import com.ismailaamassi.dailytasks.core.domain.util.ValidationUtil
import com.ismailaamassi.dailytasks.feature_auth.domain.model.LoginResult
import com.ismailaamassi.dailytasks.feature_auth.domain.repository.AuthRepository
import timber.log.Timber

class LoginUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): LoginResult {
        Timber.tag("LoginUseCase").d("invoke : email $email")
        Timber.tag("LoginUseCase").d("invoke : password $password")

        val emailError = ValidationUtil.validateEmailForLogin(email)
        val passwordError = ValidationUtil.validatePasswordForLogin(password)


        if (emailError != null || passwordError != null) {
            val loginResult =  LoginResult(
                emailError = emailError,
                passwordError = passwordError
            )

            Timber.tag("LoginUseCase").d("invoke : emailError $emailError")
            Timber.tag("LoginUseCase").d("invoke : passwordError $passwordError")

            return loginResult
        }

        val loginResult = authRepository.login(
            email = email,
            password = password
        )
        return LoginResult(result = loginResult)
    }

}
