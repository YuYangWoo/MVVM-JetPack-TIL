package com.example.hlit_ex.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hlit_ex.data.model.response.SummonerInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface SummonerDAO {
    @Query("SELECT * FROM summoner_table ORDER BY id ASC")
    fun getSummonerInfo(): Flow<List<Summoner>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSummonerInfo(summoner: Summoner)
}