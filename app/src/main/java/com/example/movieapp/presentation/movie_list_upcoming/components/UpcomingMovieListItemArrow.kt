package com.example.movieapp.presentation.movie_list_upcoming.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.movieapp.R

@Composable
fun MovieListItemArrow(modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End
    ) {
        Image(
            modifier = Modifier.size(12.dp),
            painter = painterResource(id = R.drawable.ic_arrow),
            contentDescription = "arrow"
        )
    }
}