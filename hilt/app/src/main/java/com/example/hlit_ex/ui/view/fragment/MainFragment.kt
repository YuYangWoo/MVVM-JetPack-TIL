package com.example.hlit_ex.ui.view.fragment

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.hlit_ex.R
import com.example.hlit_ex.data.model.response.LeagueResponse
import com.example.hlit_ex.data.model.response.SummonerResponse
import com.example.hlit_ex.databinding.FragmentMainBinding
import com.example.hlit_ex.ui.viewmodel.MainViewModel
import com.example.hlit_ex.util.Resource
import com.example.library.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BindingFragment<FragmentMainBinding>(R.layout.fragment_main) {
    private val mainViewModel by viewModels<MainViewModel>()
    private var summonerResponse: SummonerResponse? = null
    private var leagueResponse: List<LeagueResponse>? = null
    override fun init() {
        super.init()
        requestSummonerInfo()
        observerSummonerData()
        observerLeagueData()
    }

    private fun requestSummonerInfo() {
        mainViewModel.requestSummonerInfo("누누와 우양유")
    }

    private fun observerSummonerData() {
        mainViewModel.summonerResponse.observe(viewLifecycleOwner, Observer { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    summonerResponse = resource.data?.body() ?: return@Observer
                    mainViewModel.requestLeagueInfo(summonerResponse?.id ?: return@Observer)
                    Log.d(TAG, "observerSummonerData: ${summonerResponse}")

                }
                Resource.Status.LOADING -> {

                }
                Resource.Status.ERROR -> {
                    Log.d(TAG, "init: 실패${resource.message}")
                }
            }
        })
    }

    private fun observerLeagueData() {
        mainViewModel.leagueResponse.observe(viewLifecycleOwner, Observer { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    leagueResponse = resource.data?.body() ?: return@Observer
                    Log.d(TAG, "observerLeagueData: ${leagueResponse}")
                }
                Resource.Status.LOADING -> {
                    
                }
                Resource.Status.ERROR -> {
                    Log.d(TAG, "observerLeagueData잉?: ${resource.message}")
                }
            }
        })
    }

    companion object {
        private const val TAG = "MainFragment"
    }
}