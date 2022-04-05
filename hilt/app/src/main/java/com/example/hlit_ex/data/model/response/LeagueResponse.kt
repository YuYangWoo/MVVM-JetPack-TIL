package com.example.hlit_ex.data.model.response

data class LeagueResponse(
    val leagueId: String,
    val summonerId: String,
    val summonerName: String,
    val queueType: String,
    val tier: String,
    val rank: String,
    val leaguePoints: Int,
    val wins: Int,
    val losses: Int,
    val hotStreak: Boolean,
    val veteran: Boolean,
    val freshBlood: Boolean,
    val inactive: Boolean,
    val miniSeries: MiniSeriesDTO
)

data class MiniSeriesDTO(val losses: Int, val progress: String, val target: Int, val wins: Int)
