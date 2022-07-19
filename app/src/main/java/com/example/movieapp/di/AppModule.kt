package com.example.movieapp.di

import com.example.movieapp.common.Constants
import com.example.movieapp.data.remote.MovieDbApi
import com.example.movieapp.data.repository.MovieRepositoryImpl
import com.example.movieapp.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    @Provides
    @Singleton
    fun provideMovieDbApi(): MovieDbApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieDbApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: MovieDbApi): MovieRepository {
        return MovieRepositoryImpl(api)
    }
}