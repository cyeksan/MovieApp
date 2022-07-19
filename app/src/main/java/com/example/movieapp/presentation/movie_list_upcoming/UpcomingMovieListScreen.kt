package com.example.movieapp.presentation.movie_list_upcoming

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.movieapp.R
import com.example.movieapp.presentation.Screen
import com.example.movieapp.presentation.common.ErrorItem
import com.example.movieapp.presentation.common.ProgressBar
import com.example.movieapp.presentation.movie_list_now_playing.NowPlayingMovieListScreen
import com.example.movieapp.presentation.movie_list_upcoming.components.MovieListItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun MovieListScreen(
    navController: NavController,
    viewModel: UpcomingMovieListViewModel = hiltViewModel()
) {
    val movieList = viewModel.pager.collectAsLazyPagingItems()
    val state = rememberSwipeRefreshState(
        isRefreshing = movieList.loadState.refresh is LoadState.Loading,
    )

    SwipeRefresh(
        state = state,
        onRefresh = { movieList.refresh() },
        indicator = { refreshState, trigger ->
            SwipeRefreshIndicator(
                state = refreshState,
                refreshTriggerDistance = trigger,
                scale = true,
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                largeIndication = true,
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(), verticalArrangement = Arrangement.Top
        ) {

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    NowPlayingMovieListScreen(navController = navController)
                }
                items(movieList) { movie ->
                    if (movie != null) {
                        MovieListItem(
                            movie = movie, onItemClick = {
                                navController.navigate(Screen.MovieDetailScreen.route + "/${it.id}")
                            }
                        )
                    }
                }

                when (movieList.loadState.append) {
                    is LoadState.NotLoading -> Unit
                    is LoadState.Loading -> {
                        item {
                            ProgressBar()
                        }
                    }
                    is LoadState.Error -> {
                        item {
                            ErrorItem(errorMessage = stringResource(R.string.error_loading_data))
                        }
                    }
                }

                when (movieList.loadState.refresh) {
                    is LoadState.NotLoading -> Unit
                    is LoadState.Loading -> Unit
                    is LoadState.Error -> {
                        item {
                            (movieList.loadState.refresh as LoadState.Error).error.message?.let {
                                ErrorItem(
                                    errorMessage = it
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}