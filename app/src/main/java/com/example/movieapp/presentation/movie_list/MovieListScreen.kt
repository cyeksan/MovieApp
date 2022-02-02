package com.example.movieapp.presentation.movie_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movieapp.presentation.Screen
import com.example.movieapp.presentation.movie_list.components.MovieListItem
import com.example.movieapp.presentation.now_playing_movie_list.NowPlayingMovieListScreen

@Composable
fun MovieListScreen(
    navController: NavController,
    viewModel: MovieListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Column(
        modifier = Modifier
            .fillMaxSize(), verticalArrangement = Arrangement.Top
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                NowPlayingMovieListScreen(navController = navController)
            }
            items(state.movies) { movie ->
                MovieListItem(
                    movie = movie, onItemClick = {
                        navController.navigate(Screen.MovieDetailScreen.route + "/${it.id}")
                    }
                )
            }
        }
    }

    if (state.error.isNotBlank()) {
        Text(
            text = state.error,
            color = MaterialTheme.colors.error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        )

    }

    if (state.isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(modifier = Modifier.size(48.dp))
        }
    }
}
