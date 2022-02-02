package com.example.movieapp.presentation.movie_list_upcoming

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.common.Constants
import com.example.movieapp.common.Resource
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.use_cases.get_upcoming_movies.GetUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UpcomingMovieListViewModel @Inject constructor(
    private val getMoviesUseCase: GetUpcomingMoviesUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(UpcomingMovieListState())
    val state: State<UpcomingMovieListState> = _state


    init {
        getMovies(Constants.DEFAULT_PAGE.toString())
    }

    private fun getMovies(page: String) {
        getMoviesUseCase(page).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value =
                        UpcomingMovieListState(movies = _state.value.movies + result.data!!.results)

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
        _state.value = UpcomingMovieListState(isRefreshing = true)
        _state.value = UpcomingMovieListState(pageState = 1)
        getMovies(state.value.pageState.toString())
        _state.value = UpcomingMovieListState(isRefreshing = false)
    }

    fun fetchMoreItems(page: Int) {
        getMovies(page.toString())
    }

}
