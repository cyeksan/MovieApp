package com.example.movieapp.presentation.now_playing_movie_list

import com.example.movieapp.data.remote.dto.MovieDetailDto
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

data class NowPlayingMovieListState @OptIn(ExperimentalPagerApi::class) constructor(
    val isLoading: Boolean = false,
    val movies: List<MovieDetailDto> = emptyList(),
    val error: String = "",
    var pagerState: PagerState? = null,
    var sliderState: String? = null,
    var posterTitleState: String? = null,
    var posterContentState: String? = null,
    var idState: Int = 0

    )