package com.example.hlit_ex.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "summoner_table")
class Summoner(
    @PrimaryKey val summonerName: String,
    val profileIcon: Int,
    val tier1: String,
    val tier2: String,
    val grade: Int,
    val wins: Int,
    val losses: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Summoner

        if (summonerName != other.summonerName) return false
        if (profileIcon != other.profileIcon) return false
        if (tier1 != other.tier1) return false
        if (tier2 != other.tier2) return false
        if (grade != other.grade) return false
        if (wins != other.wins) return false
        if (losses != other.losses) return false

        return true
    }

    override fun hashCode(): Int {
        var result = summonerName.hashCode()
        result = 31 * result + profileIcon.hashCode()
        result = 31 * result + tier1.hashCode()
        result = 31 * result + tier2.hashCode()
        result = 31 * result + grade
        result = 31 * result + wins
        result = 31 * result + losses
        return result
    }
}
// 이미지, 소환사 이름, 티어, 티어, 점수, 승, 패