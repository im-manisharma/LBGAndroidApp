package com.example.lbgandroidapp.data.mappers

import com.example.lbgandroidapp.data.entities.MovieDetailsResDto
import com.example.lbgandroidapp.domain.entities.MovieDetailsUiDto
import com.example.lbgandroidapp.utils.BASE_IMAGE_URL
import javax.inject.Inject

class MovieDetailApiDataMapper @Inject constructor() {
    fun toMovieDetailsUiDto(dto: MovieDetailsResDto) : MovieDetailsUiDto {
        return MovieDetailsUiDto(
            genres = dto.getGenresText(),
            release_date = dto.release_date?:"",
            runtime = dto.runtime?:0,
            spoken_languages = dto.getSpokenLanguageText(),
            tagline = dto.tagline?:"",
            title = dto.title?:"",
            vote_average = String.format("%.1f", dto.vote_average?:0.0),
            vote_count = dto.vote_count?:0,
            overview = dto.overview?:"",
            image = "$BASE_IMAGE_URL${dto.poster_path}"
        )
    }
}