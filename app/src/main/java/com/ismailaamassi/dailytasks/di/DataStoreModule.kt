package com.ismailaamassi.dailytasks.di

import android.app.Application
import com.ismailaamassi.dailytasks.core.data.repository.SessionManagerRepositoryImpl
import com.ismailaamassi.dailytasks.core.domain.repository.SessionManagerRepository
import com.ismailaamassi.dailytasks.feature_auth.domain.use_case.ReadOnBoardingUseCase
import com.ismailaamassi.dailytasks.feature_auth.domain.use_case.SaveOnBoardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStore(app: Application): SessionManagerRepository {
        return SessionManagerRepositoryImpl(app)
    }


    @Provides
    @Singleton
    fun provideSaveOnBoardingUseCase(repository: SessionManagerRepository): SaveOnBoardingUseCase {
        return SaveOnBoardingUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideReadOnBoardingUseCase(repository: SessionManagerRepository): ReadOnBoardingUseCase {
        return ReadOnBoardingUseCase(repository)
    }
}