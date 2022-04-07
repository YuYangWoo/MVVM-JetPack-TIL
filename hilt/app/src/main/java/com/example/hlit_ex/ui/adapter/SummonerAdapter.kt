package com.example.hlit_ex.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hlit_ex.data.model.response.LeagueResponse
import com.example.hlit_ex.databinding.HolderSummonerInfoBinding
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class SummonerAdapter @Inject constructor() : ListAdapter<LeagueResponse, SummonerAdapter.LeagueHolder>(DiffSummoner) {

    inner class LeagueHolder(private val binding: HolderSummonerInfoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(leagueResponse: LeagueResponse) {
            binding.information = leagueResponse
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueHolder {
        return LeagueHolder(HolderSummonerInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: LeagueHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object DiffSummoner : DiffUtil.ItemCallback<LeagueResponse>() {
        override fun areItemsTheSame(oldItem: LeagueResponse, newItem: LeagueResponse): Boolean {
            return oldItem.leagueId == newItem.leagueId
        }

        override fun areContentsTheSame(oldItem: LeagueResponse, newItem: LeagueResponse): Boolean {
            return oldItem == newItem
        }
    }

}