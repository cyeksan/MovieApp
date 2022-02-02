package com.example.movieapp.domain.model

import com.example.movieapp.data.remote.dto.MovieDetailDto

data class Movie(val results: List<MovieDetailDto>, val page: Int)