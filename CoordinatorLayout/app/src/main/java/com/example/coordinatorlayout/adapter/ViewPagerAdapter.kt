package com.example.coordinatorlayout.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.coordinatorlayout.FavoriteMovieFragment
import com.example.coordinatorlayout.MovieListFragment
import com.example.coordinatorlayout.R
import java.lang.Exception

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    private val fragmentList = arrayOf(MovieListFragment(), FavoriteMovieFragment())
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
    companion object {
        fun getTabTile(position: Int): Int {
            return when(position) {
                0 -> R.string.tab_list
                1 -> R.string.tab_favorite
                else -> throw Exception()
            }
        }
        fun getTabIcon(position: Int): Int {
            return when(position) {
                0 -> R.drawable.ic_list
                1 -> R.drawable.ic_favorite
                else -> throw Exception()
            }
        }
    }


}
