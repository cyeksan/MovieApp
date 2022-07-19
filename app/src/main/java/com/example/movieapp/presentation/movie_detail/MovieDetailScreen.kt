package com.example.movieapp.presentation.movie_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.presentation.common.ErrorItem
import com.example.movieapp.presentation.common.PosterImage
import com.example.movieapp.presentation.common.ProgressBar
import com.example.movieapp.presentation.movie_detail.components.Detail
import com.example.movieapp.presentation.movie_detail.components.ImdbRateAndRow
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.Top
    ) {
        state.movie?.let { movieDetail ->
            movieDetail.backdropPath?.let { PosterImage(backdropPath = it) }
            ImdbRateAndRow(
                rate = movieDetail.voteAverage,
                releaseDate = movieDetail.releaseDate.extDateFormatter()
            )
            Detail(title = movieDetail.title, overview = movieDetail.overview)
        }
    }

    if (state.error.isNotBlank()) {
        ErrorItem(errorMessage = state.error)
    }

    if (state.isLoading) {
        ProgressBar()
    }

}

fun String.extDateFormatter(): String {
    val parser = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH)

    return formatter.format(parser.parse(this)!!)
}