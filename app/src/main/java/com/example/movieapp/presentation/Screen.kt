package com.example.movieapp.presentation

sealed class Screen(val route: String) {
    object UpcomingMovieListScreen: Screen("movie_list_screen")
    object NowPlayingMovieListScreen: Screen("now_playing_movie_list_screen")
    object MovieDetailScreen: Screen("movie_detail_screen")
}
