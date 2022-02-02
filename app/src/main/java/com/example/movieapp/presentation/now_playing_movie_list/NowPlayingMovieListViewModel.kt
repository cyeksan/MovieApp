package com.example.movieapp.presentation.now_playing_movie_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.Resource
import com.example.movieapp.domain.use_cases.get_now_playing_movies.GetNowPlayingMoviesUseCase
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NowPlayingMovieListViewModel @Inject constructor(
    private val getNowPlayingMovieUseCase: GetNowPlayingMoviesUseCase
) : ViewModel() {
    private val _state = mutableStateOf(NowPlayingMovieListState())
    val state: State<NowPlayingMovieListState> = _state

    init {
        getNowPlayingMovies()
    }

    @OptIn(ExperimentalPagerApi::class)
    private fun getNowPlayingMovies() {
        getNowPlayingMovieUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = NowPlayingMovieListState(movies = result.data!!.results)
                }
                is Resource.Error -> {
                    _state.value = NowPlayingMovieListState(
                        error = result.message ?: "An unexpected error occurred"
                    )

                }
                is Resource.Loading -> {
                    _state.value = NowPlayingMovieListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}