package com.refanzzzz.palmoildetection.data.remote

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiConfig {
    private const val BASE_URL = "http://192.168.54.99:8087"

    @Provides
    @Singleton
    fun getApiService(): ApiService {
        val loggingInterceptor: HttpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
        }.build()

        val retrofit = Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
            client(client)
        }.build()

        return retrofit.create(ApiService::class.java)
    }
}