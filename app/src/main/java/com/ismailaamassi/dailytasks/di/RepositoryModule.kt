package com.ismailaamassi.dailytasks.di

import com.ismailaamassi.dailytasks.data.repository.AuthRepositoryImpl
import com.ismailaamassi.dailytasks.data.repository.TaskRepositoryImpl
import com.ismailaamassi.dailytasks.data.repository.UserRepositoryImpl
import com.ismailaamassi.dailytasks.domain.repository.AuthRepository
import com.ismailaamassi.dailytasks.domain.repository.TaskRepository
import com.ismailaamassi.dailytasks.domain.repository.UserRepository
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
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository


    @Binds
    abstract fun bindTaskRepository(taskRepositoryImpl: TaskRepositoryImpl): TaskRepository


}