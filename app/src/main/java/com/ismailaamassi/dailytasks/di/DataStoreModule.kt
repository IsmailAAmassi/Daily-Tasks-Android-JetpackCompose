package com.ismailaamassi.dailytasks.di

import android.app.Application
import com.ismailaamassi.dailytasks.core.data.repository.DataStoreRepositoryImpl
import com.ismailaamassi.dailytasks.core.domain.repository.DataStoreRepository
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
    fun provideDataStore(app: Application): DataStoreRepository {
        return DataStoreRepositoryImpl(app)
    }


    @Provides
    @Singleton
    fun provideSaveOnBoardingUseCase(repository: DataStoreRepository): SaveOnBoardingUseCase {
        return SaveOnBoardingUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideReadOnBoardingUseCase(repository: DataStoreRepository): ReadOnBoardingUseCase {
        return ReadOnBoardingUseCase(repository)
    }
}