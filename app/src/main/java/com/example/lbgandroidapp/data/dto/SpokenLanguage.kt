package com.example.lbgandroidapp.data.dto

import com.google.gson.annotations.SerializedName

data class SpokenLanguage(
    @SerializedName("english_name")
    var englishName: String? = "",
    @SerializedName("name")
    var name: String? = ""
)