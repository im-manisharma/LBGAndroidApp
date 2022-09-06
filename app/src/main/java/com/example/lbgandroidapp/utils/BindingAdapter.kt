package com.example.lbgandroidapp.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:imageUrl", "app:defaultSrc")
fun ImageView.loadImageUrl(imageUrl: String?, defaultSrc: Drawable?) {
    Glide.with(context)
        .load(imageUrl)
        .thumbnail(0.3f)
        .placeholder(defaultSrc)
        .error(defaultSrc)
        .into(this)

}

