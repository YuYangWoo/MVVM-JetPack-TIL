package com.example.hlit_ex.ui.view.fragment

import androidx.navigation.fragment.navArgs
import com.example.hlit_ex.R
import com.example.hlit_ex.databinding.FragmentSummonerBinding
import com.example.library.binding.BindingFragment

class SummonerFragment : BindingFragment<FragmentSummonerBinding>(R.layout.fragment_summoner) {
    private val args: SummonerFragmentArgs by navArgs()

    override fun init() {
        super.init()
        with(binding.webView) {
            settings.javaScriptEnabled = true
            loadUrl("https://www.op.gg/summoners/kr/${args.summonerName}")
        }
    }
}