package com.ismailaamassi.dailytasks.di

import android.content.SharedPreferences
import com.ismailaamassi.dailytasks.core.domain.repository.DataStoreRepository
import com.ismailaamassi.dailytasks.core.util.Constants
import com.ismailaamassi.dailytasks.feature_auth.domain.repository.AuthRepository
import com.ismailaamassi.dailytasks.feature_auth.domain.use_case.LoginUseCase
import com.ismailaamassi.dailytasks.feature_task.data.remote.TaskApi
import com.ismailaamassi.dailytasks.feature_task.data.repository.TaskRepositoryImpl
import com.ismailaamassi.dailytasks.feature_task.domain.repository.TaskRepository
import com.ismailaamassi.dailytasks.feature_task.domain.use_case.CreateTaskUseCase
import com.ismailaamassi.dailytasks.feature_task.domain.use_case.GetTasksUseCase
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
object TaskModule {

    @Singleton
    @Provides
    fun provideTaskApi(client: OkHttpClient): TaskApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TaskApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTaskRepository(
        api: TaskApi,
        dataStoreRepository: DataStoreRepository,
        sharedPreferences: SharedPreferences
    ): TaskRepository {
        return TaskRepositoryImpl(api, dataStoreRepository, sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideCreateTaskUseCase(repository: TaskRepository): CreateTaskUseCase {
        return CreateTaskUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetTasksUseCase(repository: TaskRepository): GetTasksUseCase {
        return GetTasksUseCase(repository)
    }

}