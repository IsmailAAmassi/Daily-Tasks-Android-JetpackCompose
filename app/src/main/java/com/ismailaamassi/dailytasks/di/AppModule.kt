package com.ismailaamassi.dailytasks.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.ismailaamassi.dailytasks.data.local.DailyTaskDatabase
import com.ismailaamassi.dailytasks.domain.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideSharedPref(app: Application): SharedPreferences {
        return app.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideStockDatabase(app: Application): DailyTaskDatabase {
        return Room.databaseBuilder(
            app,
            DailyTaskDatabase::class.java,
            "daily_tasks_db.db"
        ).build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit.Builder {
        val okHttpClient = OkHttpClient.Builder()
            .callTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            })
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL.plus(Constants.API_VERSION))
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
    }

}