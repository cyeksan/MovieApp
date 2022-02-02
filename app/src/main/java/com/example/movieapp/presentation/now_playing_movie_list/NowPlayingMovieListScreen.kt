package com.example.movieapp.presentation.now_playing_movie_list

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.R
import com.example.movieapp.common.Constants
import com.example.movieapp.presentation.now_playing_movie_list.components.DotsIndicator
import com.example.movieapp.presentation.ui.theme.UnselectedDot
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NowPlayingMovieListScreen(
    viewModel: NowPlayingMovieListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    state.pagerState = rememberPagerState()

    var sliderState = viewModel.sliderState.value
    var posterTitleState = viewModel.posterTitleState.value
    var posterContentState = viewModel.posterContentState.value


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

                    0 -> {
                        sliderState = state.movies[0].posterPath as String
                        posterTitleState = state.movies[0].title
                        posterContentState = state.movies[0].overview
                    }

                    1 -> {
                        sliderState = state.movies[1].posterPath as String
                        posterTitleState = state.movies[1].title
                        posterContentState = state.movies[1].overview
                    }

                    2 -> {
                        sliderState = state.movies[2].posterPath as String
                        posterTitleState = state.movies[2].title
                        posterContentState = state.movies[2].overview
                    }
                    3 -> {
                        sliderState = state.movies[3].posterPath as String
                        posterTitleState = state.movies[3].title
                        posterContentState = state.movies[3].overview
                    }
                    4 -> {
                        sliderState = state.movies[4].posterPath as String
                        posterTitleState = state.movies[4].title
                        posterContentState = state.movies[4].overview
                    }

                }

                Box(Modifier.fillMaxSize()) {
                    GlideImage(
                        imageModel = Constants.BACKDROP_BASE_PATH + sliderState,
                        contentScale = ContentScale.Crop,
                        placeHolder = painterResource(id = R.drawable.ic_poster_placeholder),
                        error = painterResource(id = R.drawable.ic_poster_error),
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
                Column(Modifier.fillMaxSize().padding(16.dp, 0.dp), verticalArrangement = Arrangement.Bottom) {
                    Text(
                        text = posterTitleState, color = Color.White,
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        fontWeight = FontWeight.W700,
                        fontSize = 20.sp,
                        lineHeight = 24.sp)
                    Text(
                        modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp),
                        text = posterContentState, color = Color.White,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                        fontWeight = FontWeight.W500,
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    )

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