package com.example.movieapp.domain.use_cases.get_movie_detail

import com.example.movieapp.common.Resource
import com.example.movieapp.data.remote.dto.toMovieDetail
import com.example.movieapp.domain.model.MovieDetail
import com.example.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(movieId: Int): Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading())
            val movie = repository.getMovieById(movieId).toMovieDetail()
            emit(Resource.Success(movie))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    e.localizedMessage
                        ?: "Couldn't reach the server. Check your internet connection"
                )
            )

        }
    }
}