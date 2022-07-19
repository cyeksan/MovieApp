package com.example.movieapp.domain.use_cases.get_upcoming_movies

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.movieapp.data.MovieDataSource
import com.example.movieapp.data.remote.dto.MovieDetailDto
import com.example.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetUpcomingMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
) {
    val pager = Pager(
        config = PagingConfig(20)
    ) {
        MovieDataSource(repository)
    }

    operator fun invoke(): Pager<Int, MovieDetailDto> {
        return pager
    }
}