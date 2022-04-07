package com.example.hlit_ex.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Summoner::class], version = 1, exportSchema = false)
public abstract class SummonerDatabase : RoomDatabase(){
    abstract fun summonerDAO() : SummonerDAO

    companion object {
        @Volatile
        private var INSTANCE: SummonerDatabase? = null

        fun getDatabase(context: Context): SummonerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, SummonerDatabase::class.java, "summoner_database").build()
                INSTANCE = instance
                instance
            }
        }
    }
}