package com.example.movieapp.presentation.movie_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.Constants
import com.example.movieapp.common.Resource
import com.example.movieapp.domain.use_cases.get_movies.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(MovieListState())
    val state: State<MovieListState> = _state

    init {
        getMovies(Constants.DEFAULT_PAGE.toString())
    }

    private fun getMovies(page: String) {
        getMoviesUseCase(page).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value =
                        MovieListState(movies = _state.value.movies + result.data!!.results)
                }
                is Resource.Error -> {
                    _state.value = MovieListState(
                        error = result.message ?: "An unexpected error occurred"
                    )

                }
                is Resource.Loading -> {
                    _state.value = MovieListState(isLoading = true)

                }
            }
        }.launchIn(viewModelScope)
    }

    fun refresh() {
        _state.value = MovieListState(isRefreshing = true)
        _state.value = MovieListState(pageState = 1)
        getMovies(state.value.pageState.toString())
        _state.value = MovieListState(isRefreshing = false)
    }

    fun fetchMoreItems(page: String) {
        getMovies(page)

    }
}
