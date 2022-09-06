package com.example.lbgandroidapp.data.dto

import com.google.gson.annotations.SerializedName

data class MovieResultDto(
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("overview")
    var overview: String? = "",
    @SerializedName("poster_path")
    var posterPath: String? = "",
    @SerializedName("release_date")
    var releaseDate: String? = "",
    @SerializedName("title")
    var title: String? = "",
    @SerializedName("vote_average")
    var voteAverage: Double? = 0.0,
    @SerializedName("vote_count")
    var voteCount: Int? = 0
)

