package com.example.movieapp.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

val PrimaryRed = Color(0xFFc43500)
val PrimaryLight = Color(0xFF191919)
val PrimaryDark = Color(0xFF8c0000)
val SecondaryDark = Color(0xFF1e1f2e)
val Secondary = Color(0xFFfafafa)
val BackgroundColorDark = Color(0xFF2A2C3F)
val TextBlack = Color(0xFF2B2D42)
val TextGray = Color(0xFF8D99AE)
val LightGray = Color(0xFFE9ECEF)
val DarkGray = Color(0xFFADB5BD)
val UnselectedDot = Color(0x4DFFFFFF)

val TextItemColor: Color
    @Composable
    @ReadOnlyComposable
    get() = if (MaterialTheme.colors.isLight) TextBlack else LightGray