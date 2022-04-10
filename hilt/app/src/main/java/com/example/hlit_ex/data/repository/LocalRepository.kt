package com.example.hlit_ex.data.repository

import androidx.annotation.WorkerThread
import com.example.hlit_ex.data.room.Summoner
import com.example.hlit_ex.data.room.SummonerDAO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject constructor(private val summonerDAO: SummonerDAO){
    val allSummonerInfo: Flow<List<Summoner>> = summonerDAO.getSummonerInfo()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(summoner: Summoner) = summonerDAO.insertSummonerInfo(summoner)

    suspend fun delete(summoner: Summoner) = summonerDAO.deleteSummonerInfo(summoner)
}