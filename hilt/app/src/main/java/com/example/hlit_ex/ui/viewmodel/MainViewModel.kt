package com.example.hlit_ex.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.hlit_ex.data.model.response.LeagueResponse
import com.example.hlit_ex.data.model.response.SummonerResponse
import com.example.hlit_ex.data.repository.LocalRepository
import com.example.hlit_ex.data.repository.RemoteRepository
import com.example.hlit_ex.data.room.Summoner
import com.example.hlit_ex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository
) : ViewModel() {
    private var _summonerResponse = MutableLiveData<Resource<Response<SummonerResponse>>>()
    val summonerResponse: LiveData<Resource<Response<SummonerResponse>>>
        get() = _summonerResponse
    private var _leagueResponse = MutableLiveData<Resource<Response<List<LeagueResponse>>>>()
    val leagueResponse: LiveData<Resource<Response<List<LeagueResponse>>>>
        get() = _leagueResponse

    var allSummonerInfo: LiveData<List<Summoner>> = localRepository.allSummonerInfo.asLiveData()

    fun insertSummoner(summoner: Summoner) = viewModelScope.launch { localRepository.insert(summoner) }

    fun deleteSummoner(summoner: Summoner) = viewModelScope.launch { localRepository.delete(summoner) }

    fun requestSummonerInfo(summonerName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _summonerResponse.postValue(Resource.loading(null))
            try {
                _summonerResponse.postValue(
                    Resource.success(
                        remoteRepository.requestSummonerInfo(
                            summonerName
                        )
                    )
                )
            } catch (e: Exception) {
                _summonerResponse.postValue(Resource.error(null, e.message ?: "Error"))
            }
        }
    }

    fun requestLeagueInfo(encryptedSummonerId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _leagueResponse.postValue(Resource.loading(null))
            try {
                _leagueResponse.postValue(
                    Resource.success(
                        remoteRepository.requestLeagueInfo(
                            encryptedSummonerId
                        )
                    )
                )
                Log.d("TAG", leagueResponse.value.toString())
            } catch (e: Exception) {
                _leagueResponse.postValue(Resource.error(null, e.message ?: "Error"))
            }
        }
    }

}