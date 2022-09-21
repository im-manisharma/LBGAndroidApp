package com.example.lbgandroidapp.repository

import com.example.lbgandroidapp.data.datasource.MoviesService
import com.example.lbgandroidapp.data.dto.MovieResultDto
import com.example.lbgandroidapp.data.dto.TopRatedMoviesResDto
import com.example.lbgandroidapp.data.mappers.MovieApiDataMapper
import com.example.lbgandroidapp.data.repository.MoviesRepositoryImpl
import com.example.lbgandroidapp.utils.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MoviesRepositoryTest {
    private val moviesService = mockk<MoviesService>(relaxed = true)
    private lateinit var moviesRepository: MoviesRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        moviesRepository = MoviesRepositoryImpl(moviesService, MovieApiDataMapper())
    }

    @Test
    fun `when getTopRatedMoviesApi is called, return success`() = runBlocking {
        coEvery { moviesService.getTopRatedMovies("api_key") } returns TopRatedMoviesResDto(
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
        coEvery { moviesService.getTopRatedMovies("api_key") } throws IllegalStateException("Server Error")

        try {
            moviesRepository.getTopRatedMovies("api_key")
        }catch (e: Exception){
            assert(e is IllegalStateException)
        }
        Unit
    }

}