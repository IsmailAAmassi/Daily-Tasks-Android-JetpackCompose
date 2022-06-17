package com.ismailaamassi.dailytasks.di

import android.content.SharedPreferences
import com.ismailaamassi.dailytasks.core.domain.repository.DataStoreRepository
import com.ismailaamassi.dailytasks.core.util.Constants
import com.ismailaamassi.dailytasks.feature_auth.data.remote.AuthApi
import com.ismailaamassi.dailytasks.feature_auth.data.repository.AuthRepositoryImpl
import com.ismailaamassi.dailytasks.feature_auth.domain.repository.AuthRepository
import com.ismailaamassi.dailytasks.feature_auth.domain.use_case.AuthenticateUseCase
import com.ismailaamassi.dailytasks.feature_auth.domain.use_case.LoginUseCase
import com.ismailaamassi.dailytasks.feature_auth.domain.use_case.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthApi(client: OkHttpClient): AuthApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }


    @Provides
    @Singleton
    fun provideAuthRepository(
        api: AuthApi,
        dataStoreRepository: DataStoreRepository,
        sharedPreferences: SharedPreferences
    ): AuthRepository {
        return AuthRepositoryImpl(api, dataStoreRepository, sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(repository: AuthRepository): LoginUseCase {
        return LoginUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideRegisterUseCase(repository: AuthRepository): RegisterUseCase {
        return RegisterUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAuthenticateUseCase(repository: AuthRepository): AuthenticateUseCase {
        return AuthenticateUseCase(repository)
    }
}