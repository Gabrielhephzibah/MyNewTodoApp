package com.cherish.mynewtodoapp.di

import android.content.Context
import androidx.room.Room
import com.cherish.mynewtodoapp.BuildConfig
import com.cherish.mynewtodoapp.data.local.db.AppDataBase
import com.cherish.mynewtodoapp.data.local.db.NewDao
import com.cherish.mynewtodoapp.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val httpInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(httpInterceptor)
        }
        return builder.build()


    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideNewsDao(appDataBase: AppDataBase): NewDao = appDataBase.newsDao()

    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context): AppDataBase{
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "myDatabase",

        ).fallbackToDestructiveMigration()
            .build()

    }

}
