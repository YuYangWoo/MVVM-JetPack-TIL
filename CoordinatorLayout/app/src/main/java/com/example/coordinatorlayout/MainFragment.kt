package com.example.coordinatorlayout

import android.content.Context
import android.graphics.PorterDuff
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.example.coordinatorlayout.adapter.ViewPagerAdapter
import com.example.coordinatorlayout.databinding.FragmentMainBinding
import com.example.library.binding.BindingFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : BindingFragment<FragmentMainBinding>(R.layout.fragment_main) {
    private lateinit var callback: OnBackPressedCallback
    private var backKeyPressedTime: Long = 0

    override fun init() {
        super.init()
        initViewPager2()
        initTabLayout()
    }

    private fun initTabLayout() {
        val selectedTabColor = ContextCompat.getColor(requireContext(), R.color.red)
        val unSelectedTabColor = ContextCompat.getColor(requireContext(), R.color.white)
        with(binding.tabLayout) {
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.icon?.setColorFilter(selectedTabColor, PorterDuff.Mode.SRC_IN)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    tab?.icon?.setColorFilter(unSelectedTabColor, PorterDuff.Mode.SRC_IN)
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }
            })
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = resources.getString(ViewPagerAdapter.getTabTile(position))
            tab.icon = resources.getDrawable(ViewPagerAdapter.getTabIcon(position), null)
            tab?.icon?.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(R.color.white, BlendModeCompat.SRC_ATOP)
        }.attach()
    }

    private fun initViewPager2() {
        with(binding.viewPager2) {
            adapter = ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        }
    }

    // BackPressed 이벤트 정의
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
                    backKeyPressedTime = System.currentTimeMillis();
                    shortToast(requireContext(),"\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.")
                    return;
                }

                if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
                    requireActivity().finish();
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
}
