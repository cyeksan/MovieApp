package com.example.movieapp.presentation.movie_list_upcoming.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.R
import com.example.movieapp.data.remote.dto.MovieDetailDto
import com.example.movieapp.presentation.ui.theme.TextGray

@Composable
fun MovieListItemOverview(movie: MovieDetailDto) {
    Text(
        text = movie.overview,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 8.dp, 0.dp, 0.dp),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        fontFamily = FontFamily(Font(R.font.roboto_medium)),
        fontWeight = FontWeight.W500,
        fontSize = 13.sp,
        color = TextGray,
        lineHeight = 18.sp
    )
}
