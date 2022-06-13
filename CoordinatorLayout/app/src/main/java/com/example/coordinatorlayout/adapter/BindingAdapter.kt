package com.example.coordinatorlayout.adapter

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapter {
    @JvmStatic
    @BindingAdapter(value = ["imageUrl", "error"], requireAll = false)
    fun loadImg(imageView: ImageView, url: String, error: Drawable) {
        Glide.with(imageView.context).load(url).error(error).into(imageView)
    }
}
