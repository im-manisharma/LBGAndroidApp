package com.example.lbgandroidapp.data.mappers

import com.example.lbgandroidapp.data.dto.MovieDetailsResDto
import com.example.lbgandroidapp.data.dto.MovieResultDto
import com.example.lbgandroidapp.domain.entities.MovieDetailsDomainModel
import com.example.lbgandroidapp.domain.entities.MovieDomainModel
import com.example.lbgandroidapp.utils.BASE_IMAGE_URL
import javax.inject.Inject

class MovieApiDataMapper @Inject constructor() {
    fun toMovieDetailsUiDto(dto: MovieDetailsResDto) : MovieDetailsDomainModel {
        return MovieDetailsDomainModel(
            genres = dto.genres?.joinToString {
                it.name.toString()
            }?:"",
            release_date = dto.releaseDate?:"",
            runtime = dto.runtime?:0,
            spoken_languages = dto.spokenLanguages?.joinToString {
                it.name.toString()
            }?:"",
            tagline = dto.tagline?:"",
            title = dto.title?:"",
            vote_average = String.format("%.1f", dto.voteAverage?:0.0),
            vote_count = dto.voteCount?:0,
            overview = dto.overview?:"",
            image = "$BASE_IMAGE_URL${dto.posterPath}"
        )
    }

    fun toMovieDomainModelList(list: List<MovieResultDto>) = list.map {
        MovieDomainModel(
            id = it.id,
            title = it.title,
            release_date = it.releaseDate,
            imageUrl = "$BASE_IMAGE_URL${it.posterPath}",
            vote_count = it.voteCount,
            vote_average = it.voteAverage
        )
    }
}