package com.example.coordinatorlayout.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coordinatorlayout.Movie
import com.example.coordinatorlayout.NavigationDirections
import com.example.coordinatorlayout.databinding.HolderMovieBinding

class MovieAdapter : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(DiffMovie) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(HolderMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    inner class MovieViewHolder(private val binding: HolderMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setOnClick {
                binding.movie?.let { selectedMovie ->
                    it.findNavController().navigate(NavigationDirections.actionGlobalMovieInformationFragment(selectedMovie))
                }

            }
        }
        fun bind(movie: Movie) {
            binding.movie = movie
        }
    }

    object DiffMovie : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }

}
