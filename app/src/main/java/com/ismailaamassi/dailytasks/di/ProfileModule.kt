package com.ismailaamassi.dailytasks.di

import com.ismailaamassi.dailytasks.core.domain.repository.SessionManagerRepository
import com.ismailaamassi.dailytasks.core.util.Constants
import com.ismailaamassi.dailytasks.feature_profile.data.remote.ProfileApi
import com.ismailaamassi.dailytasks.feature_profile.data.repository.ProfileRepositoryImpl
import com.ismailaamassi.dailytasks.feature_profile.domain.repository.ProfileRepository
import com.ismailaamassi.dailytasks.feature_profile.domain.use_case.ProfileDetailsUseCase
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
object ProfileModule {

    @Singleton
    @Provides
    fun provideProfileApi(client: OkHttpClient): ProfileApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProfileApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(): ProfileRepository {
        return ProfileRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideProfileDetailsUseCase(
        sessionManagerRepository: SessionManagerRepository,
    ): ProfileDetailsUseCase {
        return ProfileDetailsUseCase(sessionManagerRepository)
    }
}