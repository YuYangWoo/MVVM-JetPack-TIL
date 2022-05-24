package com.example.dynamicrecyclerview

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("studentImg")
    fun loadUrl(imageView: ImageView, img: Int) {
        imageView.setImageResource(img)
    }
}
