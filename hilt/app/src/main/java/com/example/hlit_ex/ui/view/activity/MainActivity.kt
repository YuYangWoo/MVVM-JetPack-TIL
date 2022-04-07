package com.example.hlit_ex.ui.view.activity

import com.example.hlit_ex.R
import com.example.hlit_ex.databinding.ActivityMainBinding
import com.example.library.navigation.NavigationActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : NavigationActivity<ActivityMainBinding>(
    R.layout.activity_main,
    R.id.fragmentContainerView
) {

    override fun init() {
        super.init()

    }
}