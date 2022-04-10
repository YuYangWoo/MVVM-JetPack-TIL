package com.example.hlit_ex.ui.view.fragment

import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hlit_ex.R
import com.example.hlit_ex.data.model.response.LeagueResponse
import com.example.hlit_ex.data.model.response.SummonerInfo
import com.example.hlit_ex.data.model.response.SummonerResponse
import com.example.hlit_ex.data.room.Summoner
import com.example.hlit_ex.databinding.FragmentMainBinding
import com.example.hlit_ex.ui.adapter.SummonerAdapter
import com.example.hlit_ex.ui.view.dialog.ProgressDialog
import com.example.hlit_ex.ui.viewmodel.MainViewModel
import com.example.hlit_ex.util.Resource
import com.example.library.binding.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BindingFragment<FragmentMainBinding>(R.layout.fragment_main) {
    private val mainViewModel by activityViewModels<MainViewModel>()
    private var summonerResponse: SummonerResponse? = null
    private var leagueResponse: LeagueResponse? = null
    private var summonerInfo: SummonerInfo? = null
    private val progressDialog: ProgressDialog by lazy { ProgressDialog(requireContext()) }

    @Inject
    lateinit var summonerAdapter: SummonerAdapter

    override fun init() {
        super.init()
        observerSummonerData()
        observerLeagueData()
        observerRoomData()
        addSummonerInfo()
        initRecyclerView()
        clickAdapterEvent()
    }

    private fun observerSummonerData() {
        mainViewModel.summonerResponse.observe(viewLifecycleOwner, Observer { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    progressDialog.dismiss()
                    resource.data?.let { data ->
                        summonerResponse = data.body()
                    }
                    summonerResponse?.let { summonerResponse ->
                        mainViewModel.requestLeagueInfo(summonerResponse.id)
                    }
                    Log.d(TAG, "observerSummonerData: ${summonerResponse.toString()}")

                    if(resource.data?.body() == null) {
                        shortToast(requireContext(), "데이터를 가지고 있지 않습니다.")
                    }
                }
                Resource.Status.LOADING -> {
                    progressDialog.show()
                }
                Resource.Status.ERROR -> {
                    progressDialog.dismiss()
                    resource.message?.let { shortToast(requireContext(), it) }
                }
            }
        })
    }

    private fun observerLeagueData() {
        mainViewModel.leagueResponse.observe(viewLifecycleOwner, Observer { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    progressDialog.dismiss()
                    resource.data?.body()?.get(EXIST_DATA_INDEX)?.let { leagueResponse = it }
                    summonerInfo = SummonerInfo(
                        summonerResponse ?: return@Observer,
                        leagueResponse ?: return@Observer
                    )
                    summonerInfo?.let {
                        mainViewModel.insertSummoner(
                            Summoner(
                                it.leagueResponse.summonerName,
                                it.summonerResponse.profileIconId,
                                it.leagueResponse.tier,
                                it.leagueResponse.rank,
                                it.leagueResponse.leaguePoints,
                                it.leagueResponse.wins,
                                it.leagueResponse.losses
                            )
                        )
                    }
                }
                Resource.Status.LOADING -> {
                    progressDialog.show()
                }
                Resource.Status.ERROR -> {
                    progressDialog.dismiss()
                    resource.message?.let { shortToast(requireContext(), it) }
                }
            }
        })
    }

    private fun observerRoomData() {
        mainViewModel.allSummonerInfo.observe(viewLifecycleOwner, Observer {
            summonerAdapter.submitList(it.toMutableList())
        })
    }

    private fun addSummonerInfo() {
        binding.setOnAdd {
            findNavController().navigate(R.id.action_mainFragment_to_summonerInputDialog)
        }
    }

    private fun initRecyclerView() {
        with(binding.recyclerSummonerInfo) {
            layoutManager = LinearLayoutManager(requireContext())
            DividerItemDecoration(
                requireActivity().applicationContext,
                DividerItemDecoration.VERTICAL
            ).let { addItemDecoration(it) }
            adapter = summonerAdapter

            ItemTouchHelper(object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    if (viewHolder is SummonerAdapter.LeagueHolder) {
                        mainViewModel.deleteSummoner(mainViewModel.allSummonerInfo.value!![viewHolder.adapterPosition])
                    }
                }
            }).attachToRecyclerView(this)
        }
    }

    private fun clickAdapterEvent() {
        summonerAdapter.setOnItemClickEventListener(object :
            SummonerAdapter.onItemClickEventListener {
            override fun onClick(view: View, position: Int, data: Summoner) {
                val mainFragmentToSummonerFragment =
                    MainFragmentDirections.actionMainFragmentToSummonerFragment(data.summonerName)
                findNavController().navigate(mainFragmentToSummonerFragment)
            }
        })
    }

    companion object {
        private const val TAG = "MainFragment"
        private const val EXIST_DATA_INDEX = 0
    }

}
