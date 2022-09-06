package com.example.lbgandroidapp.data.dto

import com.google.gson.annotations.SerializedName

data class MovieDetailsResDto(
    @SerializedName("genres")
    var genres: List<Genre>? = listOf(),
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("overview")
    var overview: String? = "",
    @SerializedName("poster_path")
    var posterPath: String? = "",
    @SerializedName("release_date")
    var releaseDate: String? = "",
    @SerializedName("runtime")
    var runtime: Int? = 0,
    @SerializedName("spoken_languages")
    var spokenLanguages: List<SpokenLanguage>? = listOf(),
    @SerializedName("tagline")
    var tagline: String? = "",
    @SerializedName("title")
    var title: String? = "",
    @SerializedName("vote_average")
    var voteAverage: Double? = 0.0,
    @SerializedName("vote_count")
    var voteCount: Int? = 0
)