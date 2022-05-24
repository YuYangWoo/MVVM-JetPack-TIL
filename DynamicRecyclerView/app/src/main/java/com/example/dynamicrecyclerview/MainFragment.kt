package com.example.dynamicrecyclerview

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dynamicrecyclerview.databinding.FragmentMainBinding
import com.example.library.binding.BindingFragment

class MainFragment : BindingFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val studentAdapter = StudentAdapter()

    override fun init() {
        super.init()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            studentAdapter?.let {
                it.dataList = StudentData.values()
                adapter = it
            }
        }
    }
}
