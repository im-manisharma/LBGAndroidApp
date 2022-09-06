package com.example.lbgandroidapp.presentation.uistates

import com.example.lbgandroidapp.domain.entities.MovieDetailsDomainModel

// Represents different states for the AllAnime screen
sealed class MovieDetailsUiState {
    object Loading: MovieDetailsUiState()
    data class Success(val data: MovieDetailsDomainModel): MovieDetailsUiState()
    data class Error(val message: String?): MovieDetailsUiState()
}