package com.example.movieapp.presentation.now_playing_movie_list

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.common.Constants
import com.example.movieapp.presentation.movie_detail.components.PosterImage
import com.example.movieapp.presentation.now_playing_movie_list.components.DotsIndicator
import com.example.movieapp.presentation.now_playing_movie_list.components.NowPlayingMovieTitleAndContent
import com.example.movieapp.presentation.ui.theme.UnselectedDot
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NowPlayingMovieListScreen(
    viewModel: NowPlayingMovieListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    state.pagerState = rememberPagerState()


    if (state.movies.isNotEmpty()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(256.dp)
        ) {

            HorizontalPager(
                count = 5,
                state = state.pagerState!!,
                modifier = Modifier.fillMaxHeight(),
            ) { page ->
                when (page) {
                    0 -> setPageValues(0, state)
                    1 -> setPageValues(1, state)
                    2 -> setPageValues(2, state)
                    3 -> setPageValues(3, state)
                    4 -> setPageValues(4, state)
                }
                Box(Modifier.fillMaxSize()) {
                    PosterImage(posterPath = Constants.BACKDROP_BASE_PATH + state.sliderState)
                }
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(16.dp, 0.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    NowPlayingMovieTitleAndContent(state = state)

                    DotsIndicator(
                        totalDots = 5,
                        selectedIndex = state.pagerState!!.currentPage,
                        selectedColor = Color.White,
                        unSelectedColor = UnselectedDot
                    )
                }
            }
        }
    }
}

fun setPageValues(i: Int, state: NowPlayingMovieListState) {
    state.sliderState = state.movies[i].posterPath as String
    state.posterTitleState = state.movies[i].title
    state.posterContentState = state.movies[i].overview
}