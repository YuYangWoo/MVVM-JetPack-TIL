package com.example.hlit_ex.data.repository

import com.example.hlit_ex.data.api.RetroInstance

class MainRepository {
    suspend fun requestSummonerInfo(summonerName: String) = RetroInstance.client.getSummonerInfo(summonerName)
}