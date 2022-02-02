package com.example.movieapp.presentation.movie_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.R
import com.example.movieapp.presentation.ui.theme.DarkGray
import com.example.movieapp.presentation.ui.theme.TextBlack

@Composable
fun ImdbRateAndRow(rate: Double, releaseDate: String) {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(id = R.drawable.ic_imdb_logo), contentDescription = "imbd")
        Image(
            painter = painterResource(id = R.drawable.ic_rate_icon),
            contentDescription = "rate icon",
            modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
        )
        Text(text = rate.toString(), modifier = Modifier.padding(4.dp, 0.dp, 0.dp, 0.dp),
            fontFamily = FontFamily(Font(R.font.roboto_medium)),
            fontWeight = FontWeight.W500,
            fontSize = 13.sp,
            color = TextBlack,
            lineHeight = 18.sp
        )
        Text(text = "/10",
            fontFamily = FontFamily(Font(R.font.roboto_medium)),
            fontWeight = FontWeight.W500,
            fontSize = 13.sp,
            color = DarkGray,
            lineHeight = 18.sp
        )
        Image(
            painter = painterResource(id = R.drawable.ic_dot_yellow),
            contentDescription = "separator icon",
            modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
        )
        Text(text = releaseDate, modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp),
            fontFamily = FontFamily(Font(R.font.roboto_medium)),
            fontWeight = FontWeight.W500,
            fontSize = 13.sp,
            color = TextBlack,
            lineHeight = 18.sp
        )

    }
}