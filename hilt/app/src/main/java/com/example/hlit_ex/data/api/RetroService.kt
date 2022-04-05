package com.example.hlit_ex.data.api

import com.example.hlit_ex.BuildConfig
import com.example.hlit_ex.data.model.response.SummonerDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetroService {
    @GET("/lol/summoner/v4/summoners/by-name/{summonerName}?api_key=${BuildConfig.LEAGUE_OF_LEGEND_API_KEY}")
    suspend fun getSummonerInfo(@Path("summonerName") summonerName: String): Response<SummonerDTO>
}