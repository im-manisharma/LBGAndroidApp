package com.example.lbgandroidapp.mappers

import com.example.lbgandroidapp.data.dto.Genre
import com.example.lbgandroidapp.data.dto.MovieDetailsResDto
import com.example.lbgandroidapp.data.dto.MovieResultDto
import com.example.lbgandroidapp.data.mappers.MovieApiDataMapper
import com.example.lbgandroidapp.domain.entities.MovieDetailsDomainModel
import com.example.lbgandroidapp.domain.entities.MovieDomainModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieApiDataMapperTest {
    private val mapper = mockk<MovieApiDataMapper>(relaxed = true)

    @Test
    fun `test toMovieDetailsUiDto function`() = runTest {
        every { mapper.toMovieDetailsUiDto(
            MovieDetailsResDto(
                genres = listOf(
                    Genre(name = "Drama"),
                    Genre(name = "Crime"),
                )
            )
        ) } returns MovieDetailsDomainModel(
            genres = "Drama, Crime"
        )

        val res = mapper.toMovieDetailsUiDto(
            MovieDetailsResDto(
                genres = listOf(
                    Genre(name = "Drama"),
                    Genre(name = "Crime"),
                )
            )
        )
        assert(res.genres == "Drama, Crime")
    }

    @Test
    fun `test toMovieDomainModelList function`() = runTest {
        every { mapper.toMovieDomainModelList(
            list = listOf(
                MovieResultDto(
                    title = "IronMan",
                    posterPath = "/image/ironman.jpeg"
                ),
                MovieResultDto(
                    title = "Hulk",
                    posterPath = "/image/hulk.jpeg"
                )
            )
        ) } returns listOf(
            MovieDomainModel(
                title = "IronMan",
                imageUrl = "https/image/ironman.jpeg"
            ),
            MovieDomainModel(
                title = "Hulk",
                imageUrl = "https/image/hulk.jpeg"
            )
        )

        val res = mapper.toMovieDomainModelList(
            list = listOf(
                MovieResultDto(
                    title = "IronMan",
                    posterPath = "/image/ironman.jpeg"
                ),
                MovieResultDto(
                    title = "Hulk",
                    posterPath = "/image/hulk.jpeg"
                )
            )
        )
        assert(res.isNotEmpty() && !res[0].imageUrl.isNullOrEmpty() && res[0].imageUrl.toString().contains("https"))
    }
}