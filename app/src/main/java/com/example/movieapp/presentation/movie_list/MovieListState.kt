package com.example.movieapp.presentation.movie_list

import com.example.movieapp.data.remote.dto.MovieDetailDto


data class MovieListState(
    val isLoading: Boolean = false,
    val movies: List<MovieDetailDto> = emptyList(),
    val error: String = "",
    val isRefreshing: Boolean = false,
    var pageState: Int = 0,
)
