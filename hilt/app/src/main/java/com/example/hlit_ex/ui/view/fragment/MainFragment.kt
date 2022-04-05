package com.example.hlit_ex.ui.view.fragment

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.hlit_ex.R
import com.example.hlit_ex.data.api.RetroInstance
import com.example.hlit_ex.databinding.FragmentMainBinding
import com.example.hlit_ex.ui.viewmodel.MainViewModel
import com.example.hlit_ex.util.Resource
import com.example.library.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

@AndroidEntryPoint
class MainFragment : BindingFragment<FragmentMainBinding>(R.layout.fragment_main) {
    private val mainViewModel by viewModels<MainViewModel>()
    override fun init() {
        super.init()
//
        mainViewModel.requestSummonerInfo("누누와 우양유")
        mainViewModel.summonerResponse.observe(viewLifecycleOwner, Observer { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    Log.d(TAG, "init: 성공")

                    Log.d(TAG, "init: ${resource.data!!.code()}")
                    Log.d(TAG, "init: ${resource.data!!.body()}")
                }
                Resource.Status.LOADING -> {

                }
                Resource.Status.ERROR -> {
                    Log.d(TAG, "init: 실패${resource.message}")
                }
            }
        })
    }

    companion object {
        private const val TAG = "MainFragment"
    }
}