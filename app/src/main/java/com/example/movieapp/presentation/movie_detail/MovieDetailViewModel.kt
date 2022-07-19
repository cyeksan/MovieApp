package com.example.movieapp.presentation.movie_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.Constants
import com.example.movieapp.common.Resource
import com.example.movieapp.domain.use_cases.get_movie_detail.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(MovieDetailState())
    val state: State<MovieDetailState> = _state

    init {
        savedStateHandle.get<Int>(Constants.PARAM_MOVIE_ID)?.let { movieId ->
            getMovieDetail(movieId = movieId)
        }
    }

    private fun getMovieDetail(movieId: Int) {
        getMovieDetailUseCase(movieId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = MovieDetailState(movie = result.data)
                }
                is Resource.Error -> {
                    _state.value = MovieDetailState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = MovieDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
