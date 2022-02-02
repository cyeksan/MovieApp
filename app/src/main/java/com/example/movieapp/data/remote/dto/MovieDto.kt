package com.example.movieapp.data.remote.dto

import com.example.movieapp.domain.model.Movie

data class MovieDto(
    val dates: Dates,
    val page: Int,
    val results: List<MovieDetailDto>,
    val total_pages: Int,
    val total_results: Int
)

fun MovieDto.toMovie() :Movie {
    return Movie(
        results = results,
        page = page
    )
}