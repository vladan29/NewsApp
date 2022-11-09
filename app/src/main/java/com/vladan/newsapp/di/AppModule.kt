package com.vladan.newsapp.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vladan.newsapp.AppConstants
import com.vladan.newsapp.api.ApiHelper
import com.vladan.newsapp.api.ApiService
import com.vladan.newsapp.repositories.RemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun provideLoginInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
    }

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request().newBuilder()
            request.addHeader("X-Api-Key", AppConstants.NEWS_API_KEY)
            val actualRequest = request.build()
            it.proceed(actualRequest)
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loginInterceptor: HttpLoggingInterceptor,
        keyInterceptor: Interceptor
    ): OkHttpClient {
        val client: OkHttpClient.Builder = OkHttpClient.Builder()
        client.addInterceptor(loginInterceptor)
        client.addInterceptor(keyInterceptor)
        client.readTimeout(60, TimeUnit.SECONDS)
        client.connectTimeout(20, TimeUnit.SECONDS)
        client.writeTimeout(60, TimeUnit.SECONDS)
        return client.build()
    }

    @Provides
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setLenient()
        return gsonBuilder.create()
    }

    @Singleton
    @Provides
    fun provideApiService(
        client: OkHttpClient,
        gson: Gson,
    ): ApiService {
        val builder: Retrofit.Builder = Retrofit.Builder()
        return builder
            .baseUrl(AppConstants.NEWS_API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteRepository(apiHelper: RemoteRepository): ApiHelper = apiHelper

    @Singleton
    @Provides
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences =
        context.getSharedPreferences(AppConstants.PREF_NAME, Context.MODE_PRIVATE)

}