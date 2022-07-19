package com.example.movieapp.data.remote

import com.example.movieapp.data.remote.dto.MovieDetailDto
import com.example.movieapp.data.remote.dto.MovieDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDbApi {
    @GET("movie/upcoming?")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: String
    ): MovieDto

    @GET("movie/now_playing?")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: String
    ): MovieDto

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") movieId: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): MovieDetailDto
}