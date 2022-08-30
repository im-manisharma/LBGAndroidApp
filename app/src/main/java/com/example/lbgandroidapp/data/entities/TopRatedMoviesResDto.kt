package com.example.lbgandroidapp.data.entities

data class TopRatedMoviesResDto(
    var page: Int? = 0,
    var results: List<MovieResultDto>? = listOf(),
    var total_pages: Int? = 0,
    var total_results: Int? = 0
)
