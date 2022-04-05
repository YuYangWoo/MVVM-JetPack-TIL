package com.example.hlit_ex.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BaseRetrofit {
    fun getClient(baseUrl: String): Retrofit = Retrofit.Builder().baseUrl(baseUrl).client(
        OkHttpClient()
    ).addConverterFactory(GsonConverterFactory.create()).build()

}