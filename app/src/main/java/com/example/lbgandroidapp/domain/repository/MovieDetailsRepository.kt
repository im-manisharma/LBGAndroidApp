package com.example.lbgandroidapp.domain.repository

import com.example.lbgandroidapp.data.entities.MovieDetailsResDto
import com.example.lbgandroidapp.domain.entities.MovieDetailsUiDto
import com.example.lbgandroidapp.utils.Result
import kotlinx.coroutines.CoroutineDispatcher

interface MovieDetailsRepository {
    suspend fun getMovieDetails(
        apiKey: String,
        movieId: Int
    ): Result<MovieDetailsUiDto>
}