package com.example.movieapp.presentation.movie_list_upcoming

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movieapp.presentation.Screen
import com.example.movieapp.presentation.common.ErrorWarning
import com.example.movieapp.presentation.common.ProgressBar
import com.example.movieapp.presentation.movie_list_now_playing.NowPlayingMovieListScreen
import com.example.movieapp.presentation.movie_list_upcoming.components.MovieListItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.flow.collect

@Composable
fun MovieListScreen(
    navController: NavController,
    viewModel: UpcomingMovieListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val listState = rememberLazyListState()

    SwipeRefresh(
        state = rememberSwipeRefreshState(state.isRefreshing),
        onRefresh = { viewModel.refresh() },
        indicator = { refreshState, trigger ->
            SwipeRefreshIndicator(
                state = refreshState,
                refreshTriggerDistance = trigger,
                scale = true,
                backgroundColor = MaterialTheme.colors.primary,
                largeIndication = true,
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(), verticalArrangement = Arrangement.Top
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize(), state = listState) {
                item {
                    NowPlayingMovieListScreen(navController = navController)
                }
                items(viewModel.list) { movie ->
                    MovieListItem(
                        movie = movie, onItemClick = {
                            navController.navigate(Screen.MovieDetailScreen.route + "/${it.id}")
                        }
                    )

                }
            }
            listState.OnBottomReached {
                viewModel.fetchMoreItems(state.page++) // fetch more data with increasing page number
            }
        }
        if (state.error.isNotBlank()) {
            ErrorWarning(state.error)
        }
        if (state.isLoading) {
            ProgressBar()
        }
    }

}

@Composable
fun LazyListState.OnBottomReached(
    loadMore: () -> Unit
) {
    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                ?:
                return@derivedStateOf true
            lastVisibleItem.index == layoutInfo.totalItemsCount - 1
        }
    }
    LaunchedEffect(shouldLoadMore) {
        snapshotFlow { shouldLoadMore.value }.collect {
            if (it) loadMore()
        }
    }
}
