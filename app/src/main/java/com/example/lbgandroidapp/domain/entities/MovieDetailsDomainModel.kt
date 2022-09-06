package com.example.lbgandroidapp.domain.entities

data class MovieDetailsDomainModel(
    var genres: String = "",
    var release_date: String = "",
    var runtime: Int = 0,
    var spoken_languages: String = "",
    var tagline: String = "",
    var title: String = "",
    var vote_average: String = "",
    var vote_count: Int = 0,
    var overview: String ="",
    var image: String =""
)