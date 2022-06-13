package com.example.coordinatorlayout

import androidx.recyclerview.widget.GridLayoutManager
import com.example.coordinatorlayout.adapter.MovieAdapter
import com.example.coordinatorlayout.databinding.FragmentMovieListBinding
import com.example.library.binding.BindingFragment

class MovieListFragment : BindingFragment<FragmentMovieListBinding>(R.layout.fragment_movie_list) {
    override fun init() {
        super.init()
        initRecyclerView()
    }
    private fun initRecyclerView() {
        with(binding.recyclerView) {
            adapter = MovieAdapter().apply {
                submitList(MovieData.movieList)
            }
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
        }
    }
}
