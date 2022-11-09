package com.vladan.newsapp.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.vladan.newsapp.R

@BindingAdapter("loadMainImage")
fun loadMainImage(imageView: AppCompatImageView, url: String?) {
    if (url != null) {
        Picasso.get()
            .load(url)
            .error(R.mipmap.ic_launcher)
            .fit()
            .centerCrop()
            .into(imageView)
    }
}
