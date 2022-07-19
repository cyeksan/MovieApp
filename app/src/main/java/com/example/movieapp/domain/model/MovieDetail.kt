package com.example.movieapp.domain.model

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    val id: Int,
    val title: String,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("poster_path")
    val posterPath: Any?,
    @SerializedName("vote_average")
    val voteAverage: Double
)