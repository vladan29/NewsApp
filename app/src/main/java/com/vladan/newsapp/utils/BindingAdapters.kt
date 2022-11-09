package com.vladan.newsapp.utils

import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.vladan.newsapp.R
import java.lang.Exception

@BindingAdapter("loadMainImage")
fun loadMainImage(imageView: AppCompatImageView, url: String?) {

    val callback: Callback = object : Callback{
        override fun onSuccess() {
           Log.d("Loading image", "SUCCESS")
        }

        override fun onError(e: Exception?) {
            Log.d("Loading image", "ERROR" + e?.message.toString())
        }

    }
    if (url != null) {
        Picasso.get()
            .load(url)
            .error(R.mipmap.ic_launcher)
            .fit()
            .centerCrop()
            .into(imageView,callback)
    }
}
