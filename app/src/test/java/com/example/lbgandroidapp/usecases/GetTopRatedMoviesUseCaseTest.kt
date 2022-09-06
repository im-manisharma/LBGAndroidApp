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
    private val mMoviesRepository = mockk<MoviesRepository>(relaxed = true)
    private lateinit var mGetTopRatedMoviesUseCase: GetTopRatedMoviesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mGetTopRatedMoviesUseCase = GetTopRatedMoviesUseCase(mMoviesRepository)
    }

    @Test
    fun `when invoke called, return success`() = runBlocking {
        coEvery { mMoviesRepository.getTopRatedMovies("api_key") } returns Result.Success(
            listOf()
        )
        val res = mGetTopRatedMoviesUseCase.invoke("api_key")
        assert(res is Result.Success)
    }

    @Test
    fun `when invoke called, return error`() = runBlocking {
        coEvery { mMoviesRepository.getTopRatedMovies("api_key") } returns Result.Error(
            Exception("error_message")
        )
        val res = mGetTopRatedMoviesUseCase.invoke("api_key")
        assert(res is Result.Error)
    }
}