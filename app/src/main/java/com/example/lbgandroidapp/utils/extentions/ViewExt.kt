package com.example.lbgandroidapp.utils.extentions

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.doVisible() {
    visibility = View.VISIBLE
}

fun View.doGone() {
    visibility = View.GONE
}

fun View.doInvisible() {
    visibility = View.INVISIBLE
}

fun ImageView.loadImage(
    imageUrl: Any?,
    errorResId: Int?
) {

    if(context == null) return
    if (imageUrl == null) return
    if (errorResId == null) return

    Glide.with(context)
        .load(imageUrl)
        .thumbnail(0.3f)
        .placeholder(errorResId)
        .error(errorResId)
        .into(this)


}