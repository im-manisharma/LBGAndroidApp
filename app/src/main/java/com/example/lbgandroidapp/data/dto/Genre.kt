package com.example.lbgandroidapp.data.dto

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("name")
    var name: String? = ""
)