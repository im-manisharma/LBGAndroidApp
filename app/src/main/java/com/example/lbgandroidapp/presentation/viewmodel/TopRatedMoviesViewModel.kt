package com.example.lbgandroidapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lbgandroidapp.di.ApiKey
import com.example.lbgandroidapp.di.IoDispatcher
import com.example.lbgandroidapp.domain.usecases.GetTopRatedMoviesUseCase
import com.example.lbgandroidapp.presentation.uistates.TopRatedMoviesUiState
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
    private val _uiState = MutableStateFlow<TopRatedMoviesUiState>(TopRatedMoviesUiState.Loading)
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