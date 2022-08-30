package com.example.lbgandroidapp.data.repository

import com.example.lbgandroidapp.data.data_source.MoviesService
import com.example.lbgandroidapp.data.entities.MovieResultDto
import com.example.lbgandroidapp.domain.repository.MoviesRepository
import com.example.lbgandroidapp.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepositoryImpl(
    private val apiService: MoviesService
) : MoviesRepository {
    override suspend fun getTopRatedMovies(
        apiKey: String
    ): Result<List<MovieResultDto>> = try {
        val response = apiService.getTopRatedMovies(apiKey)
        Result.Success(response.results?: arrayListOf())
    } catch (e: Exception) {
        Result.Error(e)
    }
}