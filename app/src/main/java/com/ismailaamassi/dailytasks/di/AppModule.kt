package com.ismailaamassi.dailytasks.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.google.gson.Gson
import com.ismailaamassi.dailytasks.core.data.DailyTaskDatabase
import com.ismailaamassi.dailytasks.core.domain.repository.DataStoreRepository
import com.ismailaamassi.dailytasks.core.util.Constants
import com.ismailaamassi.dailytasks.core.util.task_checker.DefaultTaskChecker
import com.ismailaamassi.dailytasks.core.util.task_checker.TaskChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPref(app: Application): SharedPreferences {
        return app.getSharedPreferences(
            Constants.SHARED_PREF_NAME,
            Context.MODE_PRIVATE
        )
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(
        dataStoreRepository: DataStoreRepository,
        sharedPreferences: SharedPreferences
    ): OkHttpClient {
        /*val r = runBlocking { dataStoreRepository.readLoggedUserToken() }
        val token = r.map {
            it
        }*/

        return OkHttpClient.Builder()
            .addInterceptor {
                val token = sharedPreferences.getString(Constants.KEY_JWT_TOKEN, "")
                val modifiedRequest = it.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                it.proceed(modifiedRequest)
            }
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
            )
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()
    }


    @Provides
    @Singleton
    fun provideDailyTaskDatabase(app: Application): DailyTaskDatabase {
        return Room.databaseBuilder(
            app,
            DailyTaskDatabase::class.java,
            "daily_tasks_db.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskChecker(): TaskChecker {
        return DefaultTaskChecker()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    /* @Provides
     @Singleton
     fun provideFaker(): Faker {
         return Faker()
     }*/

}