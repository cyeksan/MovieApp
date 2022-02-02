package com.example.movieapp.domain.repository

import com.example.movieapp.data.remote.dto.MovieDetailDto
import com.example.movieapp.data.remote.dto.MovieDto2

interface MovieRepository {
    suspend fun getMovies(): MovieDto2
    suspend fun getNowPlayingMovies(): MovieDto2
    suspend fun getMovieById(movieId: Int) : MovieDetailDto
}