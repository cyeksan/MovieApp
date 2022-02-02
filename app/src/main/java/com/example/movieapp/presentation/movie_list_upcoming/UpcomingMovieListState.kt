package com.example.movieapp.presentation.movie_list_upcoming

import com.example.movieapp.data.remote.dto.MovieDetailDto


data class UpcomingMovieListState(
    val isLoading: Boolean = false,
    val movies: List<MovieDetailDto> = emptyList(),
    val error: String = "",
    val isRefreshing: Boolean = false,
    var page: Int = 1,
)
