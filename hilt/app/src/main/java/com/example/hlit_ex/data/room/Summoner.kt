package com.example.hlit_ex.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hlit_ex.data.model.response.SummonerInfo

@Entity(tableName = "summoner_table")
class Summoner(@PrimaryKey val id: String) {
}