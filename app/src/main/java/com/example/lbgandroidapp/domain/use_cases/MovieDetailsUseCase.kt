package com.example.lbgandroidapp.domain.use_cases

import com.example.lbgandroidapp.domain.repository.MovieDetailsRepository
import com.example.lbgandroidapp.domain.repository.MoviesRepository
import javax.inject.Inject

class MovieDetailsUseCase @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository
) {
    suspend operator fun invoke(apiKey: String, movieId: Int) = movieDetailsRepository.getMovieDetails(apiKey, movieId)
}