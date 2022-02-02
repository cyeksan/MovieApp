package com.example.movieapp.presentation.movie_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.presentation.movie_detail.components.Detail
import com.example.movieapp.presentation.movie_detail.components.ImdbRateAndRow
import com.example.movieapp.presentation.movie_detail.components.PosterImage
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.Top ) {
        state.movie?.let { movieDetail ->
            PosterImage(posterPath = movieDetail.posterPath)
            ImdbRateAndRow(rate = movieDetail.voteAverage, releaseDate = movieDetail.releaseDate.extDateFormatter())
            Detail(title = movieDetail.title, overview = movieDetail.overview)
        }
    }

    if (state.error.isNotBlank()) {
        Text(
            text = state.error,
            color = MaterialTheme.colors.error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
        )

    }

    if (state.isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(modifier = Modifier.size(48.dp))
        }
    }

}

fun String.extDateFormatter() : String {
    val parser = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH)

    return formatter.format(parser.parse(this)!!)
}