package com.example.movieapp.presentation.movie_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.R
import com.example.movieapp.presentation.ui.theme.TextBlack

@Composable
fun Detail(title: String, overview: String) {
    Column(modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 0.dp)) {
        Text(
            text = title,
            fontFamily = FontFamily(Font(R.font.roboto_bold)),
            fontWeight = FontWeight.W700,
            fontSize = 20.sp,
            color = TextBlack,
            lineHeight = 24.sp
        )

        Text(
            text = overview,
            modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 8.dp),
            fontFamily = FontFamily(Font(R.font.roboto_regular)),
            fontWeight = FontWeight.W400,
            fontSize = 15.sp,
            color = TextBlack,
            lineHeight = 20.sp
        )
    }
}