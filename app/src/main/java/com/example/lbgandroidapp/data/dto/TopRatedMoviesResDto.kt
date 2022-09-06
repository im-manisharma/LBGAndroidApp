package com.example.lbgandroidapp.data.dto

import com.google.gson.annotations.SerializedName

data class TopRatedMoviesResDto(
    @SerializedName("results")
    var results: List<MovieResultDto>? = listOf()
)
