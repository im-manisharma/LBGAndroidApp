package com.example.lbgandroidapp.domain.use_cases

import com.example.lbgandroidapp.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(
        apiKey: String
    ) = moviesRepository.getTopRatedMovies(apiKey)
}