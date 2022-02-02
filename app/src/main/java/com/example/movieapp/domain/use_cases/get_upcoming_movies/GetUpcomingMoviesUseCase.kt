package com.example.movieapp.domain.use_cases.get_upcoming_movies

import com.example.movieapp.common.Resource
import com.example.movieapp.data.remote.dto.toMovie
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUpcomingMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(page: String): Flow<Resource<Movie>> = flow {
        try {
            emit(Resource.Loading())
            val movies = repository.getUpcomingMovies(page).toMovie()
            emit(Resource.Success(movies))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred" ))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach the server. Check your internet connection" ))

        }
    }
}