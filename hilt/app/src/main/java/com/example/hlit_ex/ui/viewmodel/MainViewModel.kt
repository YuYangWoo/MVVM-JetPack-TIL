package com.example.hlit_ex.ui.viewmodel

import androidx.lifecycle.*
import com.example.hlit_ex.data.model.response.SummonerDTO
import com.example.hlit_ex.data.repository.MainRepository
import com.example.hlit_ex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    private var _summonerResponse = MutableLiveData<Resource<Response<SummonerDTO>>>()
    val summonerResponse: LiveData<Resource<Response<SummonerDTO>>>
        get() = _summonerResponse

    fun requestSummonerInfo(summonerName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _summonerResponse.postValue(Resource.loading(null))
            try {
                _summonerResponse.postValue(
                    Resource.success(
                        mainRepository.requestSummonerInfo(
                            summonerName
                        )
                    )
                )
            } catch (e: Exception) {
                _summonerResponse.postValue(Resource.error(null, e.message ?: "Error"))
            }
        }
    }
}