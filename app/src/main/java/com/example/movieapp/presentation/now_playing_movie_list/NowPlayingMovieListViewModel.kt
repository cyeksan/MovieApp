package com.example.movieapp.presentation.now_playing_movie_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.Resource
import com.example.movieapp.domain.use_cases.get_movie.GetMovieUseCase
import com.example.movieapp.domain.use_cases.get_now_playing_movies.GetNowPlayingMoviesUseCase
import com.example.movieapp.presentation.movie_detail.MovieDetailState
import com.example.movieapp.presentation.movie_list.MovieListState
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NowPlayingMovieListViewModel @Inject constructor(
    private val getNowPlayingMovieUseCase: GetNowPlayingMoviesUseCase) : ViewModel() {
    private val _state = mutableStateOf(NowPlayingMovieListState())
    val state: State<NowPlayingMovieListState> = _state

    private val _sliderState = mutableStateOf("")
    val sliderState: State<String> = _sliderState

    private val _posterTitleState = mutableStateOf("")
    val posterTitleState: State<String> = _posterTitleState
    private val _posterContentState = mutableStateOf("")
    val posterContentState: State<String> = _posterContentState

    init {
        getNowPlayingMovies()
    }

    private fun getNowPlayingMovies() {
        getNowPlayingMovieUseCase().onEach {result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = NowPlayingMovieListState(movies = result.data!!.results)
                    _sliderState.value = result.data.results[0].posterPath as String
                    _posterTitleState.value = result.data.results[0].title
                    _posterContentState.value = result.data.results[0].overview


                }
                is Resource.Error -> {
                    _state.value = NowPlayingMovieListState(error = result.message ?:
                    "An unexpected error occurred")

                }
                is Resource.Loading -> {
                    _state.value = NowPlayingMovieListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}