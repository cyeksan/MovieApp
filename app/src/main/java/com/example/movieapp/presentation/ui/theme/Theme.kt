package com.example.movieapp.presentation.ui.theme

import android.graphics.Color
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.White

private val DarkColorPalette = darkColors(
    primary = PrimaryRed,
    primaryVariant = PrimaryDark,
    secondary = SecondaryDark,
    background = BackgroundColorDark,
)

private val LightColorPalette = lightColors(
    primary = PrimaryRed,
    primaryVariant = PrimaryLight,
    secondary = Secondary,
    background = White,
)
@Composable
fun MovieAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}