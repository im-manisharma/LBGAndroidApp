package com.example.lbgandroidapp.data.repository

import com.example.lbgandroidapp.data.data_source.MoviesService
import com.example.lbgandroidapp.data.entities.MovieDetailsResDto
import com.example.lbgandroidapp.data.entities.MovieResultDto
import com.example.lbgandroidapp.data.mappers.MovieDetailApiDataMapper
import com.example.lbgandroidapp.domain.entities.MovieDetailsUiDto
import com.example.lbgandroidapp.domain.repository.MovieDetailsRepository
import com.example.lbgandroidapp.domain.repository.MoviesRepository
import com.example.lbgandroidapp.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieDetailsRepositoryImpl(
    private val apiService: MoviesService,
    private val mapper: MovieDetailApiDataMapper
) : MovieDetailsRepository {
    override suspend fun getMovieDetails(
        apiKey: String,
        movieId: Int
    ): Result<MovieDetailsUiDto> = try {
        val response = apiService.getMovieDetails(movieId, apiKey)
        Result.Success(mapper.toMovieDetailsUiDto(response))
    } catch (e: Exception) {
        Result.Error(e)
    }
}