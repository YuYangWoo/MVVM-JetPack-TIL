package com.example.hlit_ex.data.repository

import com.example.hlit_ex.data.api.RetroInstance
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRepository @Inject constructor(){
    suspend fun requestSummonerInfo(summonerName: String) = RetroInstance.client.getSummonerInfo(summonerName)
    suspend fun requestLeagueInfo(encryptedSummonerId: String) = RetroInstance.client.getLeagueInfo(encryptedSummonerId)
}