package com.example.lbgandroidapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lbgandroidapp.di.ApiKey
import com.example.lbgandroidapp.di.IoDispatcher
import com.example.lbgandroidapp.domain.usecases.MovieDetailsUseCase
import com.example.lbgandroidapp.presentation.uistates.MovieDetailsUiState
import com.example.lbgandroidapp.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: MovieDetailsUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    @ApiKey private val apiKey: String
) : ViewModel() {
    var movieId: Int = 0

    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow<MovieDetailsUiState>(MovieDetailsUiState.Loading)
    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<MovieDetailsUiState> = _uiState

    fun getMovieDetails() {
        viewModelScope.launch(dispatcher) {
            _uiState.value = MovieDetailsUiState.Loading
            when(val result = getMovieDetailsUseCase.invoke(apiKey, movieId)){
                is Result.Success -> {
                    _uiState.value = MovieDetailsUiState.Success(result.data)
                }
                is Result.Error -> {
                    _uiState.value = MovieDetailsUiState.Error(result.exception.message)
                }
            }
        }
    }
}