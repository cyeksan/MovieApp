package com.example.movieapp.presentation.movie_list_upcoming.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.common.Constants
import com.example.movieapp.data.remote.dto.MovieDetailDto
import com.example.movieapp.presentation.ui.theme.LightGray
import com.skydoves.landscapist.glide.GlideImage
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MovieListItem(movie: MovieDetailDto, onItemClick: (MovieDetailDto) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onItemClick(movie) }
        .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween) {
        Column(
            modifier = Modifier
                .size(104.dp)
                .align(CenterVertically)
        ) {
            GlideImage(
                imageModel = Constants.BACKDROP_BASE_PATH + movie.backdropPath,
                contentScale = ContentScale.Crop,
                placeHolder = painterResource(id = R.drawable.ic_backdrop_placeholder),
                error = painterResource(id = R.drawable.ic_backdrop_error)
            )
        }
        Column(modifier = Modifier.fillMaxWidth(0.87f)) {
            MovieListItemTitle(movie = movie)
            MovieListItemOverview(movie = movie)
            MovieListItemReleaseDate(movie = movie)
        }
      MovieListItemArrow(modifier = Modifier
          .fillMaxSize()
          .align(CenterVertically))
    }
    Divider(color = LightGray, thickness = 1.dp, modifier = Modifier.padding(16.dp, 0.dp))
}

fun String.extDateFormatter(): String {
    val parser = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH)

    return formatter.format(parser.parse(this)!!)
}



