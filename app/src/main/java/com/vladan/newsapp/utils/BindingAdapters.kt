package com.vladan.newsapp.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.vladan.newsapp.R

@BindingAdapter("loadMainImage")
fun loadMainImage(imageView: AppCompatImageView, url: String?) {

    Glide.with(imageView.context)
        .load(url)
        .error(R.mipmap.ic_launcher)
        .centerCrop()
        .into(imageView)

}
