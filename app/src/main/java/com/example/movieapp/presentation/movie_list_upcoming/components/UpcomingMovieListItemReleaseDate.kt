package com.example.movieapp.presentation.movie_list_upcoming.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.R
import com.example.movieapp.data.remote.dto.MovieDetailDto
import com.example.movieapp.presentation.ui.theme.TextGray

@Composable
fun MovieListItemReleaseDate(movie: MovieDetailDto) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 15.dp, 0.dp, 0.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = movie.releaseDate.extDateFormatter(),
            fontFamily = FontFamily(Font(R.font.roboto_medium)),
            fontWeight = FontWeight.W500,
            fontSize = 12.sp,
            color = TextGray,
            lineHeight = 16.sp
        )
    }
}