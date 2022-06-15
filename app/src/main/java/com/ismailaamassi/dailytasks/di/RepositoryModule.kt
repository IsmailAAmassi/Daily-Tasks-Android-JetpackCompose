package com.ismailaamassi.dailytasks.di

import com.ismailaamassi.dailytasks.feature_auth.data.repository.AuthRepositoryImpl
import com.ismailaamassi.dailytasks.feature_task.data.repository.TaskRepositoryImpl
import com.ismailaamassi.dailytasks.feature_profile.data.repository.ProfileRepositoryImpl
import com.ismailaamassi.dailytasks.feature_auth.domain.repository.AuthRepository
import com.ismailaamassi.dailytasks.feature_profile.domain.repository.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository


    @Binds
    abstract fun bindProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl): ProfileRepository


    @Binds
    abstract fun bindTaskRepository(taskRepositoryImpl: TaskRepositoryImpl): TaskRepositoryImpl


}