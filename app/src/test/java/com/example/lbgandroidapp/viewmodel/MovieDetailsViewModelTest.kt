package com.example.lbgandroidapp.viewmodel

import com.example.lbgandroidapp.domain.entities.MovieDetailsDomainModel
import com.example.lbgandroidapp.domain.usecases.MovieDetailsUseCase
import com.example.lbgandroidapp.presentation.uistates.MovieDetailsUiState
import com.example.lbgandroidapp.presentation.viewmodel.MovieDetailsViewModel
import com.example.lbgandroidapp.utils.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailsViewModelTest {
    private val testDispatcher = UnconfinedTestDispatcher()
    lateinit var movieDetailsViewModel: MovieDetailsViewModel
    private val movieDetailsUseCase=  mockk<MovieDetailsUseCase>(relaxed = true)

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        movieDetailsViewModel = MovieDetailsViewModel(
            movieDetailsUseCase,
            testDispatcher,
            "api_key"
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
    }

    @Test
    fun `when apiCalled, return success`() = runBlocking {
        coEvery { movieDetailsUseCase.invoke("api_key", 123) } returns Result.Success(
            MovieDetailsDomainModel()
        )
        movieDetailsViewModel.movieId = 123
        movieDetailsViewModel.getMovieDetails()

        assert(movieDetailsViewModel.uiState.value is MovieDetailsUiState.Success)
    }

    @Test
    fun `when apiCalled, return error`() = runBlocking {
        coEvery { movieDetailsUseCase.invoke("api_key", 123) } returns Result.Error(
            Exception("error message")
        )
        movieDetailsViewModel.movieId = 123
        movieDetailsViewModel.getMovieDetails()

        assert(movieDetailsViewModel.uiState.value is MovieDetailsUiState.Error)
    }
}