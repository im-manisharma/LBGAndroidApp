package com.example.lbgandroidapp.domain.repository

import com.example.lbgandroidapp.data.entities.MovieResultDto
import com.example.lbgandroidapp.utils.Result
import kotlinx.coroutines.CoroutineDispatcher

interface MoviesRepository {
    suspend fun getTopRatedMovies(
        apiKey: String
    ): Result<List<MovieResultDto>>
}