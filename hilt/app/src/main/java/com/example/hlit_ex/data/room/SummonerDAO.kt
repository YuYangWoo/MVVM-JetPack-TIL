package com.example.hlit_ex.data.room

import androidx.room.*
import com.example.hlit_ex.data.model.response.SummonerInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface SummonerDAO {
    @Query("SELECT * FROM summoner_table ORDER BY summonerName ASC")
    fun getSummonerInfo(): Flow<List<Summoner>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSummonerInfo(summoner: Summoner)

    @Delete
    suspend fun deleteSummonerInfo(summoner: Summoner)
}