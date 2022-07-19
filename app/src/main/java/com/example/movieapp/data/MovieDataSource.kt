package com.example.movieapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.data.remote.dto.MovieDetailDto
import com.example.movieapp.domain.repository.MovieRepository

class MovieDataSource(
    private val repository: MovieRepository
) : PagingSource<Int, MovieDetailDto>() {
    override fun getRefreshKey(state: PagingState<Int, MovieDetailDto>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1)
            // If you return page?.nextKey?.plus(1) instead of null as above,
            // the page number will be incremented by 1 in case of swipe refresh.
            // For more info: https://stackoverflow.com/a/70550847
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDetailDto> {
        return try {
            val page = params.key ?: 1
            val response = repository.getUpcomingMovies(page.toString())
            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = if (response.results.isNotEmpty()) response.page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}