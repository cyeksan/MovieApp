package com.example.movieapp

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.common.Constants
import com.example.movieapp.presentation.Screen
import com.example.movieapp.presentation.movie_detail.MovieDetailScreen
import com.example.movieapp.presentation.movie_list.MovieListScreen
import com.example.movieapp.presentation.now_playing_movie_list.NowPlayingMovieListScreen
import com.example.movieapp.presentation.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        ) // To make the content appear behind the status bar
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.MovieListScreen.route) {

                        composable(
                            route = Screen.NowPlayingMovieListScreen.route
                        ) {
                            NowPlayingMovieListScreen(navController = navController)
                        }
                        composable(
                            route = Screen.MovieListScreen.route
                        ) {
                            MovieListScreen(navController = navController)
                        }

                        composable(
                            route = Screen.MovieDetailScreen.route + "/{movieId}",
                            arguments = listOf(navArgument("movieId") { type = NavType.IntType })

                        ) {
                            MovieDetailScreen()
                        }
                    }
                }
            }
        }
    }
}
