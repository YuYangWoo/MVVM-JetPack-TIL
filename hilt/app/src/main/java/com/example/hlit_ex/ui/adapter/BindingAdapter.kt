package com.example.hlit_ex.ui.adapter

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("profileIcon")
    fun setProfileIcon(imageView: ImageView, number: Int) {
        Glide.with(imageView.context)
            .load("https://ddragon.leagueoflegends.com/cdn/12.6.1/img/profileicon/${number}.png")
            .into(imageView)
    }
}
