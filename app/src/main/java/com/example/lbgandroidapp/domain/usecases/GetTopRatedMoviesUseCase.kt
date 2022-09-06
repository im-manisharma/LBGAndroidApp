package com.example.lbgandroidapp.domain.usecases

import com.example.lbgandroidapp.domain.repository.MoviesRepository
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(
        apiKey: String
    ) = moviesRepository.getTopRatedMovies(apiKey)
}