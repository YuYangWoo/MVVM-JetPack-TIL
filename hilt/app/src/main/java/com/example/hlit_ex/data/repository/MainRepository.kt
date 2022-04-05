package com.example.hlit_ex.data.repository

import com.example.hlit_ex.data.api.RetroInstance
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(){
    suspend fun requestSummonerInfo(summonerName: String) = RetroInstance.client.getSummonerInfo(summonerName)
}