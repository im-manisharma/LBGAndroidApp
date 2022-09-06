package com.example.lbgandroidapp.domain.repository

import com.example.lbgandroidapp.domain.entities.MovieDomainModel
import com.example.lbgandroidapp.utils.Result

interface MoviesRepository {
    suspend fun getTopRatedMovies(
        apiKey: String
    ): Result<List<MovieDomainModel>>
}