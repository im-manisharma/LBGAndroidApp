package com.example.lbgandroidapp.repository

import com.example.lbgandroidapp.data.data_source.MoviesService
import com.example.lbgandroidapp.data.entities.MovieResultDto
import com.example.lbgandroidapp.data.entities.TopRatedMoviesResDto
import com.example.lbgandroidapp.data.repository.MoviesRepositoryImpl
import com.example.lbgandroidapp.utils.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MoviesRepositoryTest {
    private val mMoviesService = mockk<MoviesService>(relaxed = true)
    private lateinit var moviesRepository: MoviesRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        moviesRepository = MoviesRepositoryImpl(mMoviesService)
    }

    @Test
    fun `when getTopRatedMoviesApi is called, return success`() = runBlocking {
        coEvery { mMoviesService.getTopRatedMovies("api_key") } returns TopRatedMoviesResDto(
            page = 1,
            results = listOf(
                MovieResultDto(
                    title = "IronMan"
                )
            )
        )

        val res = moviesRepository.getTopRatedMovies("api_key")
        assert(res is Result.Success && res.data.isNotEmpty() && res.data[0].title == "IronMan")
    }

    @Test
    fun `when getTopRatedMoviesApi is called, return error`() = runBlocking {
        coEvery { mMoviesService.getTopRatedMovies("api_key") } throws IllegalStateException("Server Error")

        try {
            moviesRepository.getTopRatedMovies("api_key")
        }catch (e: Exception){
            assert(e is IllegalStateException)
        }
        Unit
    }

}