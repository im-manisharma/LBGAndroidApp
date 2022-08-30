package com.example.lbgandroidapp.utils.extentions

import android.content.Context
import android.widget.Toast

fun Context.showToastMsg(str: String?) {
    Toast.makeText(this, str.toString(), Toast.LENGTH_SHORT).show()
}