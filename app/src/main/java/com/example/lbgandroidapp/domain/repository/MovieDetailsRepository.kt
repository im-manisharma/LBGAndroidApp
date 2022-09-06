package com.example.lbgandroidapp.domain.repository

import com.example.lbgandroidapp.domain.entities.MovieDetailsDomainModel
import com.example.lbgandroidapp.utils.Result

interface MovieDetailsRepository {
    suspend fun getMovieDetails(
        apiKey: String,
        movieId: Int
    ): Result<MovieDetailsDomainModel>
}