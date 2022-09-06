package com.example.lbgandroidapp.domain.entities

data class MovieDomainModel(
    var id: Int? = 0,
    var overview: String? = "",
    var imageUrl: String? = "",
    var release_date: String? = "",
    var title: String? = "",
    var vote_average: Double? = 0.0,
    var vote_count: Int? = 0
)