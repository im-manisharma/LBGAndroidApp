package com.example.lbgandroidapp.presentation.uistates

import com.example.lbgandroidapp.domain.entities.MovieDomainModel

// Represents different states for the AllAnime screen
sealed class TopRatedMoviesUiState {
    object Loading: TopRatedMoviesUiState()
    data class Success(val movieList: List<MovieDomainModel>): TopRatedMoviesUiState()
    data class Error(val message: String?): TopRatedMoviesUiState()
}
