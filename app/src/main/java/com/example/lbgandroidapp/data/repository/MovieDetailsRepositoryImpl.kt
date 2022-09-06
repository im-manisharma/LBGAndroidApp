package com.example.lbgandroidapp.data.repository

import com.example.lbgandroidapp.data.datasource.MoviesService
import com.example.lbgandroidapp.data.mappers.MovieApiDataMapper
import com.example.lbgandroidapp.domain.entities.MovieDetailsDomainModel
import com.example.lbgandroidapp.domain.repository.MovieDetailsRepository
import com.example.lbgandroidapp.utils.Result

class MovieDetailsRepositoryImpl(
    private val apiService: MoviesService,
    private val mapper: MovieApiDataMapper
) : MovieDetailsRepository {
    override suspend fun getMovieDetails(
        apiKey: String,
        movieId: Int
    ): Result<MovieDetailsDomainModel> = try {
        val response = apiService.getMovieDetails(movieId, apiKey)
        Result.Success(mapper.toMovieDetailsUiDto(response))
    } catch (e: Exception) {
        Result.Error(e)
    }
}