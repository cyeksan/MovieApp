package com.example.movieapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.R
import com.example.movieapp.common.Constants

@Composable
fun PosterImage(posterPath: Any?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(256.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(Constants.BACKDROP_BASE_PATH + posterPath)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.ic_poster_placeholder),
            error = painterResource(id = R.drawable.ic_poster_error),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.colorMatrix(
                ColorMatrix(
                    floatArrayOf(
                        0.60f, 0f, 0f, 0f, 0f,
                        0f, 0.60f, 0f, 0f, 0f,
                        0f, 0f, 0.60f, 0f, 0f,
                        0f, 0f, 0f, 1f, 0f
                    )
                )
            )
        )
    }
}