package com.ismailaamassi.dailytasks.feature_auth.data.repository

import android.content.SharedPreferences
import com.ismailaamassi.dailytasks.R
import com.ismailaamassi.dailytasks.core.domain.repository.SessionManagerRepository
import com.ismailaamassi.dailytasks.core.util.Constants
import com.ismailaamassi.dailytasks.core.util.Resource
import com.ismailaamassi.dailytasks.core.util.SimpleResource
import com.ismailaamassi.dailytasks.core.util.UiText
import com.ismailaamassi.dailytasks.feature_auth.data.remote.AuthApi
import com.ismailaamassi.dailytasks.feature_auth.data.remote.request.LoginRequest
import com.ismailaamassi.dailytasks.feature_auth.data.remote.request.RegisterRequest
import com.ismailaamassi.dailytasks.feature_auth.domain.repository.AuthRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val sessionManagerRepository: SessionManagerRepository,
    private val sharedPreferences: SharedPreferences
) : AuthRepository {

    override suspend fun register(
        username: String,
        email: String,
        password: String
    ): SimpleResource {
        val request = RegisterRequest(username, email, password)
        return try {
            val response = authApi.register(request)
            if (response.successful) {
                Resource.Success(Unit)
            } else {
                response.message?.let { msg ->
                    Resource.Error(uiText = UiText.DynamicString(msg))
                } ?: Resource.Error(uiText = UiText.StringResource(R.string.error_unknown))
            }
        } catch (e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch (e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_oops_something_went_wrong)
            )
        }
    }

    override suspend fun login(email: String, password: String): SimpleResource {
        val request = LoginRequest(email, password)
        return try {
            val response = authApi.login(request)
            if (response.successful) {
                response.data?.let { authResponse ->
                    sharedPreferences.edit()
                        .putString(Constants.KEY_JWT_TOKEN, authResponse.token)
                        .putString(Constants.KEY_USER_ID, authResponse.userId)
                        .putString(Constants.KEY_USER_NAME, authResponse.userName)
                        .apply()

                    sessionManagerRepository.saveLoggedUserToken(authResponse.token)
                    sessionManagerRepository.saveLoggedUserName(authResponse.userName)
                    sessionManagerRepository.saveLoggedUserId(authResponse.userId)
                }
                Resource.Success(Unit)
            } else {
                response.message?.let { msg ->
                    Resource.Error(uiText = UiText.DynamicString(msg))
                } ?: Resource.Error(uiText = UiText.StringResource(R.string.error_unknown))
            }
        } catch (e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch (e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_oops_something_went_wrong)
            )
        }
    }

    override suspend fun authenticate(): SimpleResource {
        return try {
            authApi.authenticate()
            Resource.Success(Unit)
        } catch (e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch (e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_oops_something_went_wrong)
            )
        }
    }
}