package com.example.lbgandroidapp.usecases

import com.example.lbgandroidapp.domain.entities.MovieDetailsDomainModel
import com.example.lbgandroidapp.domain.repository.MovieDetailsRepository
import com.example.lbgandroidapp.domain.usecases.MovieDetailsUseCase
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
            MovieDetailsDomainModel()
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