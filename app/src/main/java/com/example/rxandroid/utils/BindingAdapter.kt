package com.example.rxandroid.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (url != null) {
        Glide.with(view.context)
            .load("https://image.tmdb.org/t/p/original/$url")
            .centerCrop()
            .into(view)
    }
}