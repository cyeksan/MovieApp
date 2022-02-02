package com.example.movieapp.presentation.movie_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.common.Constants
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun PosterImage(posterPath: Any) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(256.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        GlideImage(
            imageModel = Constants.POSTER_BASE_PATH + posterPath,
            contentScale = ContentScale.Crop,
            placeHolder = painterResource(id = R.drawable.ic_poster_placeholder),
            error = painterResource(id = R.drawable.ic_poster_error)
        )

    }
}