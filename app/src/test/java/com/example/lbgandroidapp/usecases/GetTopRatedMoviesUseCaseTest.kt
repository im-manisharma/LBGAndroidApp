package com.example.lbgandroidapp.usecases

import com.example.lbgandroidapp.domain.repository.MoviesRepository
import com.example.lbgandroidapp.domain.usecases.GetTopRatedMoviesUseCase
import com.example.lbgandroidapp.utils.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetTopRatedMoviesUseCaseTest {
    private val moviesRepository = mockk<MoviesRepository>(relaxed = true)
    private lateinit var getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getTopRatedMoviesUseCase = GetTopRatedMoviesUseCase(moviesRepository)
    }

    @Test
    fun `when invoke called, return success`() = runBlocking {
        coEvery { moviesRepository.getTopRatedMovies("api_key") } returns Result.Success(
            listOf()
        )
        val res = getTopRatedMoviesUseCase.invoke("api_key")
        assert(res is Result.Success)
    }

    @Test
    fun `when invoke called, return error`() = runBlocking {
        coEvery { moviesRepository.getTopRatedMovies("api_key") } returns Result.Error(
            Exception("error_message")
        )
        val res = getTopRatedMoviesUseCase.invoke("api_key")
        assert(res is Result.Error)
    }
}