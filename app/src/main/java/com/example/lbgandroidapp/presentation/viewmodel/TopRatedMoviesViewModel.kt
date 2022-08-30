package com.example.lbgandroidapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lbgandroidapp.data.entities.MovieResultDto
import com.example.lbgandroidapp.di.ApiKey
import com.example.lbgandroidapp.di.IoDispatcher
import com.example.lbgandroidapp.domain.use_cases.GetTopRatedMoviesUseCase
import com.example.lbgandroidapp.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopRatedMoviesViewModel @Inject constructor(
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    @ApiKey private val apiKey: String
) : ViewModel() {

    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow<TopRatedMoviesUiState>(TopRatedMoviesUiState.Success(emptyList()))
    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<TopRatedMoviesUiState> = _uiState

    init {
        getTopRatedMovies()
    }

    fun getTopRatedMovies(){
        viewModelScope.launch(dispatcher) {
            _uiState.value = TopRatedMoviesUiState.Loading
            when(val result = getTopRatedMoviesUseCase.invoke(apiKey)){
                is Result.Success -> {
                    _uiState.value = TopRatedMoviesUiState.Success(result.data)
                }
                is Result.Error -> {
                    _uiState.value = TopRatedMoviesUiState.Error(result.exception.message)
                }
            }
        }
    }
}

// Represents different states for the AllAnime screen
sealed class TopRatedMoviesUiState {
    object Loading: TopRatedMoviesUiState()
    data class Success(val movieList: List<MovieResultDto>): TopRatedMoviesUiState()
    data class Error(val message: String?): TopRatedMoviesUiState()
}