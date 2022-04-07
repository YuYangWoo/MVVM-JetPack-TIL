package com.example.hlit_ex.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hlit_ex.data.model.response.LeagueResponse
import com.example.hlit_ex.data.model.response.SummonerInfo
import com.example.hlit_ex.databinding.HolderSummonerInfoBinding
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class SummonerAdapter @Inject constructor() : ListAdapter<SummonerInfo, SummonerAdapter.LeagueHolder>(DiffSummoner) {

    inner class LeagueHolder(private val binding: HolderSummonerInfoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(summonerInfo: SummonerInfo) {
            binding.summonerInfo = summonerInfo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueHolder {
        return LeagueHolder(HolderSummonerInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: LeagueHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object DiffSummoner : DiffUtil.ItemCallback<SummonerInfo>() {
        override fun areItemsTheSame(oldItem: SummonerInfo, newItem: SummonerInfo): Boolean {
            return oldItem.leagueResponse.leagueId == newItem.leagueResponse.leagueId
        }

        override fun areContentsTheSame(oldItem: SummonerInfo, newItem: SummonerInfo): Boolean {
            return oldItem == newItem
        }
    }

}