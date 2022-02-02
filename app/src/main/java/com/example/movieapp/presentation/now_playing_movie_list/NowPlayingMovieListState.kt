package com.example.movieapp.presentation.now_playing_movie_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.movieapp.R
import com.example.movieapp.data.remote.dto.MovieDetailDto
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

data class NowPlayingMovieListState @OptIn(ExperimentalPagerApi::class) constructor(
    val isLoading: Boolean = false,
    val movies: List<MovieDetailDto> = emptyList(),
    val error: String = "",
    var pagerState: PagerState? = null
)