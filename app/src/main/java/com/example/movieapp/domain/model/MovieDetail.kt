package com.example.movieapp.domain.model

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    val id: Int,
    val title: String,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("backdrop_path")
    val backdropPath: Any?,
    @SerializedName("vote_average")
    val voteAverage: Double
)