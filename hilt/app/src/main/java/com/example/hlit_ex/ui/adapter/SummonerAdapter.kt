package com.example.hlit_ex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hlit_ex.data.room.Summoner
import com.example.hlit_ex.databinding.HolderSummonerInfoBinding
import dagger.hilt.android.scopes.FragmentScoped
import java.lang.Exception
import javax.inject.Inject

@FragmentScoped
class SummonerAdapter @Inject constructor() : ListAdapter<Summoner, SummonerAdapter.LeagueHolder>(DiffSummoner) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueHolder {
        return LeagueHolder(HolderSummonerInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: LeagueHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private lateinit var onItemClick: onItemClickEventListener

    interface onItemClickEventListener {
        fun onClick(view: View, position: Int, data: Summoner)
    }

    fun setOnItemClickEventListener(listener: onItemClickEventListener) {
        this.onItemClick = listener
    }

    inner class LeagueHolder(private val binding: HolderSummonerInfoBinding) : RecyclerView.ViewHolder(binding.root) {
       init {
           binding.root.setOnClickListener {
               try {
                   onItemClick.onClick(it, adapterPosition, binding.summoner!!)
               } catch (e: Exception) {
                   e.printStackTrace()
               }
           }
       }
        fun bind(summoner: Summoner) {
            binding.summoner = summoner
        }
    }

    object DiffSummoner : DiffUtil.ItemCallback<Summoner>() {
        override fun areItemsTheSame(oldItem: Summoner, newItem: Summoner): Boolean {
            return oldItem.summonerName == newItem.summonerName
        }

        override fun areContentsTheSame(oldItem: Summoner, newItem: Summoner): Boolean {
            return oldItem == newItem
        }
    }

}
