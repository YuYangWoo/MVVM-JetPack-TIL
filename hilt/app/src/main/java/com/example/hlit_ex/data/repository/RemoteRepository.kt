package com.example.hlit_ex.data.repository

import com.example.hlit_ex.data.api.RetroService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRepository @Inject constructor(private val service: RetroService){
    suspend fun requestSummonerInfo(summonerName: String) = service.getSummonerInfo(summonerName)
    suspend fun requestLeagueInfo(encryptedSummonerId: String) = service.getLeagueInfo(encryptedSummonerId)
}
