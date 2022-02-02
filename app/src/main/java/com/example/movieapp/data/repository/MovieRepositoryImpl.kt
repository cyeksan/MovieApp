package com.example.movieapp.data.repository

import com.example.movieapp.common.Constants
import com.example.movieapp.data.remote.MovieDbApi
import com.example.movieapp.data.remote.dto.MovieDetailDto
import com.example.movieapp.data.remote.dto.MovieDto
import com.example.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api: MovieDbApi) : MovieRepository {
    override suspend fun getMovies(page: String): MovieDto {
        return api.getMovies(Constants.TEST_API_KEY, Constants.LANGUAGE, page)
    }

    override suspend fun getNowPlayingMovies(): MovieDto {
        return api.getNowPlayingMovies(Constants.TEST_API_KEY, Constants.LANGUAGE, Constants.DEFAULT_PAGE.toString())
    }

    override suspend fun getMovieById(
        movieId: Int
    ): MovieDetailDto {
        return api.getMovieDetail(movieId, Constants.TEST_API_KEY, Constants.LANGUAGE)
    }

}