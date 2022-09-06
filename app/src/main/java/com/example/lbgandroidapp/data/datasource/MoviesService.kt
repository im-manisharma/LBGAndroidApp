package com.example.lbgandroidapp.data.datasource

import com.example.lbgandroidapp.data.dto.MovieDetailsResDto
import com.example.lbgandroidapp.data.dto.TopRatedMoviesResDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {
    @GET("top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): TopRatedMoviesResDto

    @GET("{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): MovieDetailsResDto
}