package com.example.hlit_ex.ui.view.fragment

import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.hlit_ex.R
import com.example.hlit_ex.data.model.response.LeagueResponse
import com.example.hlit_ex.data.model.response.SummonerResponse
import com.example.hlit_ex.databinding.FragmentMainBinding
import com.example.hlit_ex.ui.view.dialog.ProgressDialog
import com.example.hlit_ex.ui.viewmodel.MainViewModel
import com.example.hlit_ex.util.Resource
import com.example.library.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BindingFragment<FragmentMainBinding>(R.layout.fragment_main) {
    private val mainViewModel by activityViewModels<MainViewModel>()
    private var summonerResponse: SummonerResponse? = null
    private var leagueResponse: List<LeagueResponse>? = null
    private val progressDialog: ProgressDialog by lazy { ProgressDialog(requireContext()) }

    override fun init() {
        super.init()
        observerLeagueData()
        addSummonerInfo()
    }

    private fun observerLeagueData() {
        mainViewModel.leagueResponse.observe(viewLifecycleOwner, Observer { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    progressDialog.dismiss()
                    leagueResponse = resource.data?.body() ?: return@Observer
                    Log.d(TAG, "observerLeagueData: ${leagueResponse}")
                }
                Resource.Status.LOADING -> {
                    progressDialog.show()

                }
                Resource.Status.ERROR -> {
                    Log.d(TAG, "observerLeagueData잉?: ${resource.message}")
                }
            }
        })
    }

   private fun addSummonerInfo() {
       binding.btnSummonerAdd.setOnClickListener {
           findNavController().navigate(R.id.action_mainFragment_to_summonerInputDialog)
       }
    }

    companion object {
        private const val TAG = "MainFragment"
    }
}