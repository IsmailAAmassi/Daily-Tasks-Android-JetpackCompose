package com.ismailaamassi.dailytasks.di

import android.content.SharedPreferences
import com.google.gson.Gson
import com.ismailaamassi.dailytasks.core.domain.repository.DataStoreRepository
import com.ismailaamassi.dailytasks.core.util.Constants
import com.ismailaamassi.dailytasks.feature_task.data.remote.TaskApi
import com.ismailaamassi.dailytasks.feature_task.data.repository.TaskRepositoryImpl
import com.ismailaamassi.dailytasks.feature_task.domain.repository.TaskRepository
import com.ismailaamassi.dailytasks.feature_task.domain.use_case.*
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
        gson: Gson,
        dataStoreRepository: DataStoreRepository,
        sharedPreferences: SharedPreferences
    ): TaskRepository {
        return TaskRepositoryImpl(api, gson, dataStoreRepository, sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideTasksUseCase(repository: TaskRepository): TaskUseCases {
        return TaskUseCases(
            changeCheckTaskUseCase = ChangeCheckTaskUseCase(repository),
            createTaskUseCase = CreateTaskUseCase(repository = repository),
            deleteTaskUseCase = DeleteTaskUseCase(repository = repository),
            getTasksUseCase = GetTasksUseCase(repository = repository),
            getTaskDetailsUseCase = GetTaskDetailsUseCase(repository = repository),
            restoreTaskUseCase = RestoreTaskUseCase(repository = repository),
            updateTaskUseCase = UpdateTaskUseCase(repository = repository)
        )
    }

}