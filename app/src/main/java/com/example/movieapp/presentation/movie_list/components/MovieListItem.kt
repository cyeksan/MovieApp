package com.example.movieapp.presentation.movie_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp.R
import com.example.movieapp.common.Constants
import com.example.movieapp.data.remote.dto.MovieDetailDto
import com.example.movieapp.presentation.ui.theme.LightGray
import com.example.movieapp.presentation.ui.theme.TextBlack
import com.example.movieapp.presentation.ui.theme.TextGray
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
            Text(
                text = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp, 8.dp, 0.dp, 0.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                fontWeight = FontWeight.W700,
                fontSize = 15.sp,
                color = TextBlack,
                lineHeight = 20.sp

            )
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(CenterVertically),
            horizontalAlignment = Alignment.End
        ) {
            Image(
                modifier = Modifier.size(12.dp),
                painter = painterResource(id = R.drawable.ic_arrow),
                contentDescription = "arrow"
            )
        }
    }
    Divider(color = LightGray, thickness = 1.dp, modifier = Modifier.padding(16.dp, 0.dp))
}

fun String.extDateFormatter(): String {
    val parser = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH)

    return formatter.format(parser.parse(this)!!)
}



