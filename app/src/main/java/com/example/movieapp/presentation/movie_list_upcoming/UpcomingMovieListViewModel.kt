package com.example.movieapp.presentation.movie_list_upcoming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.movieapp.domain.use_cases.get_upcoming_movies.GetUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpcomingMovieListViewModel @Inject constructor(
    getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
) : ViewModel() {
    val pager = getUpcomingMoviesUseCase.pager.flow.cachedIn(viewModelScope)
}
