package com.example.dynamicrecyclerview

import android.os.Bundle
import com.example.dynamicrecyclerview.databinding.ActivityMainBinding
import com.example.library.navigation.NavigationActivity

class MainActivity : NavigationActivity<ActivityMainBinding>(R.layout.activity_main, R.id.fragmentContainerView) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
