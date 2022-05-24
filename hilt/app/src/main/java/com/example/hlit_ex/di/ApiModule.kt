package com.example.hlit_ex.di

import com.example.hlit_ex.data.api.RetroService
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
class ApiModule {
    val baseUrl = "https://kr.api.riotgames.com"

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): RetroService = retrofit.create(RetroService::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder().baseUrl(baseUrl).client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create()).build()

}
