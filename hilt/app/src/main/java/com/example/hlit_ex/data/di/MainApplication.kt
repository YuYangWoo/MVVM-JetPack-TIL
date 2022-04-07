package com.example.hlit_ex.data.di

import android.app.Application
import com.example.hlit_ex.data.repository.LocalRepository
import com.example.hlit_ex.data.room.SummonerDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {
}