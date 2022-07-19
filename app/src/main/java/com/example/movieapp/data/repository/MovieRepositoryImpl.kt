package com.example.movieapp.data.repository

import com.example.movieapp.common.Constants
import com.example.movieapp.data.remote.MovieDbApi
import com.example.movieapp.data.remote.dto.MovieDetailDto
import com.example.movieapp.data.remote.dto.MovieDto
import com.example.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api: MovieDbApi) : MovieRepository {
    override suspend fun getUpcomingMovies(page: String): MovieDto {
        delay(1000)
        return api.getUpcomingMovies(Constants.TEST_API_KEY, Constants.LANGUAGE, page)
    }

    override suspend fun getNowPlayingMovies(): MovieDto {
        return api.getNowPlayingMovies(
            Constants.TEST_API_KEY,
            Constants.LANGUAGE,
            Constants.DEFAULT_PAGE.toString()
        )
    }

    override suspend fun getMovieById(
        movieId: Int
    ): MovieDetailDto {
        return api.getMovieDetail(movieId, Constants.TEST_API_KEY, Constants.LANGUAGE)
    }
}