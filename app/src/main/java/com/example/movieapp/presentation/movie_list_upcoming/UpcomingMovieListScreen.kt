package com.example.movieapp.presentation.movie_list_upcoming

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movieapp.presentation.Screen
import com.example.movieapp.presentation.movie_list_now_playing.NowPlayingMovieListScreen
import com.example.movieapp.presentation.movie_list_upcoming.components.MovieListItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.InternalCoroutinesApi
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
                // Pass the SwipeRefreshState + trigger through
                state = refreshState,
                refreshTriggerDistance = trigger,
                // Enable the scale animation
                scale = true,
                // Change the color and shape
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
                items(state.movies) { movie ->
                    MovieListItem(
                        movie = movie, onItemClick = {
                            navController.navigate(Screen.MovieDetailScreen.route + "/${it.id}")
                        }
                    )
                    listState.OnBottomReached {
                        // do on load more
                        viewModel.fetchMoreItems(state.pageState++)
                    }
                }
            }

        }

        if (state.error.isNotBlank()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                )
            }
        }

        if (state.isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = Modifier.size(48.dp))
            }
        }
    }

}

@OptIn(InternalCoroutinesApi::class)
@Composable
fun LazyListState.OnBottomReached(
    loadMore: () -> Unit
) {
    // state object which tells us if we should load more
    val shouldLoadMore = remember {
        derivedStateOf {

            // get last visible item
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                ?:
                // list is empty
                // return false here if loadMore should not be invoked if the list is empty
                return@derivedStateOf true

            // Check if last visible item is the last item in the list
            lastVisibleItem.index == layoutInfo.totalItemsCount - 1
        }
    }

    // Convert the state into a cold flow and collect
    LaunchedEffect(shouldLoadMore) {
        snapshotFlow { shouldLoadMore.value }.collect {
            if (it) loadMore()

        }
    }
}
