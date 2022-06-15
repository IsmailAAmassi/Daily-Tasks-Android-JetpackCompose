package com.ismailaamassi.dailytasks.di

import com.ismailaamassi.dailytasks.data.remote.auth.AuthApi
import com.ismailaamassi.dailytasks.data.remote.task.TaskApi
import com.ismailaamassi.dailytasks.data.remote.user.UserApi
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
    fun provideUserApi(retrofit: Retrofit.Builder): UserApi =
        retrofit.build().create(UserApi::class.java)


    @Singleton
    @Provides
    fun provideTaskApi(retrofit: Retrofit.Builder): TaskApi =
        retrofit.build().create(TaskApi::class.java)
}