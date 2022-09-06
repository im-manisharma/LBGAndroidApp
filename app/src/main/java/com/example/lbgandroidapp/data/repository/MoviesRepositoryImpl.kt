package com.example.lbgandroidapp.data.repository

import com.example.lbgandroidapp.data.datasource.MoviesService
import com.example.lbgandroidapp.data.mappers.MovieApiDataMapper
import com.example.lbgandroidapp.domain.entities.MovieDomainModel
import com.example.lbgandroidapp.domain.repository.MoviesRepository
import com.example.lbgandroidapp.utils.Result

class MoviesRepositoryImpl(
    private val apiService: MoviesService,
    private val mapper: MovieApiDataMapper
) : MoviesRepository {
    override suspend fun getTopRatedMovies(
        apiKey: String
    ): Result<List<MovieDomainModel>> = try {
        val response = apiService.getTopRatedMovies(apiKey)
        Result.Success(mapper.toMovieDomainModelList(response.results?: listOf()))
    } catch (e: Exception) {
        Result.Error(e)
    }
}