package com.example.hlit_ex.di

import android.content.Context
import com.example.hlit_ex.data.room.SummonerDAO
import com.example.hlit_ex.data.room.SummonerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): SummonerDatabase {
        return SummonerDatabase.getDatabase(context)
    }
    @Singleton
    @Provides
    fun provideSummonerDao(summonerDatabase: SummonerDatabase): SummonerDAO {
        return summonerDatabase.summonerDAO()
    }
}
