package com.example.lbgandroidapp.use_cases

import com.example.lbgandroidapp.data.entities.MovieDetailsResDto
import com.example.lbgandroidapp.domain.entities.MovieDetailsUiDto
import com.example.lbgandroidapp.domain.repository.MovieDetailsRepository
import com.example.lbgandroidapp.domain.use_cases.MovieDetailsUseCase
import com.example.lbgandroidapp.presentation.viewmodel.MovieDetailsUiState
import com.example.lbgandroidapp.utils.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.lang.Exception

class MoviesDetailsUseCaseTest {
    private val mMovieDetailsRepo = mockk<MovieDetailsRepository>(relaxed = true)
    private lateinit var mMoviesDetailsUseCaseTest: MovieDetailsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mMoviesDetailsUseCaseTest = MovieDetailsUseCase(mMovieDetailsRepo)
    }

    @Test
    fun `when invoke called, return success`() = runBlocking {
        coEvery { mMovieDetailsRepo.getMovieDetails("api_key", 123) } returns Result.Success(
            MovieDetailsUiDto()
        )
        val res = mMoviesDetailsUseCaseTest.invoke("api_key", 123)
        assert(res is Result.Success)
    }

    @Test
    fun `when invoke called, return error`() = runBlocking {
        coEvery { mMovieDetailsRepo.getMovieDetails("api_key", 123) } returns Result.Error(
            Exception("error_message")
        )
        val res = mMoviesDetailsUseCaseTest.invoke("api_key", 123)
        assert(res is Result.Error)
    }
}