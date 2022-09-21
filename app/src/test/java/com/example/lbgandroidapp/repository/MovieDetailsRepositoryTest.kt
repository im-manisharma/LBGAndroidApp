package com.example.lbgandroidapp.repository

import com.example.lbgandroidapp.data.datasource.MoviesService
import com.example.lbgandroidapp.data.dto.MovieDetailsResDto
import com.example.lbgandroidapp.data.mappers.MovieApiDataMapper
import com.example.lbgandroidapp.data.repository.MovieDetailsRepositoryImpl
import com.example.lbgandroidapp.utils.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MovieDetailsRepositoryTest {
    private val moviesService = mockk<MoviesService>(relaxed = true)
    private lateinit var movieDetailsRepository: MovieDetailsRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        movieDetailsRepository = MovieDetailsRepositoryImpl(moviesService, MovieApiDataMapper())
    }

    @Test
    fun `when getMovieDetailsApi is called, return success`() = runBlocking {
        coEvery { moviesService.getMovieDetails(123, "api_key") } returns MovieDetailsResDto(
            id = 123,
            title = "IronMan",
        )

        val res = movieDetailsRepository.getMovieDetails("api_key", 123)
        assert(res is Result.Success && res.data.title == "IronMan")
    }

    @Test
    fun `when getMovieDetailsApi is called, return error`() = runBlocking {
        coEvery { moviesService.getMovieDetails(123, "api_key") } throws IllegalStateException("Server Error")
        try {
            movieDetailsRepository.getMovieDetails("api_key", 123)
        }catch (e: Exception){
            assert(e is IllegalStateException)
        }
        Unit
    }
}