package com.example.movieapp.presentation.movie_list_upcoming

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.Constants
import com.example.movieapp.common.Resource
import com.example.movieapp.data.remote.dto.MovieDetailDto
import com.example.movieapp.domain.use_cases.get_upcoming_movies.GetUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UpcomingMovieListViewModel @Inject constructor(
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(UpcomingMovieListState())
    val state: State<UpcomingMovieListState> = _state
    var list = mutableListOf<MovieDetailDto>()

    init {
        getUpcomingMovies(Constants.DEFAULT_PAGE.toString())
    }

    private fun getUpcomingMovies(page: String) {
        getUpcomingMoviesUseCase(page).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value =
                        UpcomingMovieListState(movies = result.data!!.results)

                    if (page != 1.toString()) list.addAll(result.data.results)
                    else list = result.data.results.toMutableList()
                }
                is Resource.Error -> {
                    _state.value = UpcomingMovieListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = UpcomingMovieListState(isLoading = true)

                }
            }
        }.launchIn(viewModelScope)
    }

    fun refresh() {
        list.clear()
        _state.value = UpcomingMovieListState(isRefreshing = true)
        _state.value = UpcomingMovieListState(page = 1)
        getUpcomingMovies(state.value.page.toString())
        _state.value = UpcomingMovieListState(isRefreshing = false)
    }

    fun fetchMoreItems(page: Int) {
        getUpcomingMovies(page.toString())
    }
}
