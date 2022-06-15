package com.ismailaamassi.dailytasks.di

import com.ismailaamassi.dailytasks.feature_auth.data.remote.AuthApi
import com.ismailaamassi.dailytasks.feature_task.data.remote.TaskApi
import com.ismailaamassi.dailytasks.feature_profile.data.remote.ProfileApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideAuthApi(retrofit: Retrofit.Builder): AuthApi =
        retrofit.build().create(AuthApi::class.java)


    @Singleton
    @Provides
    fun provideUserApi(retrofit: Retrofit.Builder): ProfileApi =
        retrofit.build().create(ProfileApi::class.java)


    @Singleton
    @Provides
    fun provideTaskApi(retrofit: Retrofit.Builder): TaskApi =
        retrofit.build().create(TaskApi::class.java)
}