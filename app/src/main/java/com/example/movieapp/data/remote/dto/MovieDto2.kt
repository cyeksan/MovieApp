package com.example.movieapp.data.remote.dto

import com.example.movieapp.domain.model.Movie2

data class MovieDto2(
    val dates: Dates,
    val page: Int,
    val results: List<MovieDetailDto>,
    val total_pages: Int,
    val total_results: Int
)

fun MovieDto2.toMovie() :Movie2 {
    return Movie2(
        results = results
    )
}