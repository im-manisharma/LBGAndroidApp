package com.example.lbgandroidapp.domain.usecases

import com.example.lbgandroidapp.domain.repository.MovieDetailsRepository
import javax.inject.Inject

class MovieDetailsUseCase @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository
) {
    suspend operator fun invoke(apiKey: String, movieId: Int) = movieDetailsRepository.getMovieDetails(apiKey, movieId)
}