package com.example.hlit_ex.data.api

object RetroInstance {
    private val baseUrl = "https://kr.api.riotgames.com"
    val client = BaseRetrofit.getClient(baseUrl).create(RetroService::class.java)
}