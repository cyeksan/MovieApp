package com.example.movieapp.presentation.now_playing_movie_list.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.R
import com.example.movieapp.presentation.now_playing_movie_list.NowPlayingMovieListState

@Composable
fun NowPlayingMovieTitleAndContent(state: NowPlayingMovieListState) {
    state.posterTitleState?.let {
        Text(
            text = it, color = Color.White,
            fontFamily = FontFamily(Font(R.font.roboto_bold)),
            fontWeight = FontWeight.W700,
            fontSize = 20.sp,
            lineHeight = 24.sp
        )
    }
    state.posterContentState?.let {
        Text(
            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp),
            text = it, color = Color.White,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontFamily = FontFamily(Font(R.font.roboto_medium)),
            fontWeight = FontWeight.W500,
            fontSize = 12.sp,
            lineHeight = 16.sp
        )
    }
}