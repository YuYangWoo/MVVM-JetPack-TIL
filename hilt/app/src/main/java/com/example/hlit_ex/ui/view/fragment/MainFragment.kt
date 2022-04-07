package com.example.hlit_ex.ui.view.fragment

import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hlit_ex.R
import com.example.hlit_ex.data.model.response.LeagueResponse
import com.example.hlit_ex.data.model.response.SummonerResponse
import com.example.hlit_ex.databinding.FragmentMainBinding
import com.example.hlit_ex.ui.adapter.SummonerAdapter
import com.example.hlit_ex.ui.view.dialog.ProgressDialog
import com.example.hlit_ex.ui.view.dialog.SummonerInputDialog
import com.example.hlit_ex.ui.viewmodel.MainViewModel
import com.example.hlit_ex.util.Resource
import com.example.library.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BindingFragment<FragmentMainBinding>(R.layout.fragment_main) {
    private val mainViewModel by activityViewModels<MainViewModel>()
    private var summonerResponse: SummonerResponse? = null
    private var leagueResponse: ArrayList<LeagueResponse>? = ArrayList()
    private val progressDialog: ProgressDialog by lazy { ProgressDialog(requireContext()) }
    @Inject
    lateinit var summonerAdapter: SummonerAdapter

    override fun init() {
        super.init()
        observerSummonerData()
        observerLeagueData()
        addSummonerInfo()
        initRecyclerView()
    }

    private fun observerSummonerData() {
        mainViewModel.summonerResponse.observe(viewLifecycleOwner, Observer { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    progressDialog.dismiss()
                    summonerResponse = resource.data?.body() ?: return@Observer
                    mainViewModel.requestLeagueInfo(summonerResponse!!.id)
                }
                Resource.Status.LOADING -> {
                    progressDialog.show()
                }
                Resource.Status.ERROR -> {
                    progressDialog.dismiss()
                    Log.d(TAG, "init: 실패${resource.message}")
                }
            }
        })
    }

    private fun observerLeagueData() {
        mainViewModel.leagueResponse.observe(viewLifecycleOwner, Observer { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    progressDialog.dismiss()
                    resource.data?.body()?.get(0)?.let { leagueResponse?.add(it) }
                    summonerAdapter.submitList(leagueResponse?.toMutableList())
                }
                Resource.Status.LOADING -> {
                    progressDialog.show()

                }
                Resource.Status.ERROR -> {
                    progressDialog.dismiss()
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

    private fun initRecyclerView() {
        with(binding.recyclerSummonerInfo) {
            layoutManager = LinearLayoutManager(requireContext())
            val decoration = DividerItemDecoration(requireActivity().applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            adapter = summonerAdapter
        }
    }

    companion object {
        private const val TAG = "MainFragment"
    }
}