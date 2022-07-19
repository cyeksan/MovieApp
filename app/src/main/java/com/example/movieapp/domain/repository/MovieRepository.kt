package com.example.movieapp.domain.repository

import com.example.movieapp.data.remote.dto.MovieDetailDto
import com.example.movieapp.data.remote.dto.MovieDto

interface MovieRepository {
    suspend fun getUpcomingMovies(page: String): MovieDto
    suspend fun getNowPlayingMovies(): MovieDto
    suspend fun getMovieById(movieId: Int): MovieDetailDto
}