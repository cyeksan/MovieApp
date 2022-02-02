package com.example.movieapp.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.movieapp.R

val Roboto = FontFamily(
    fonts = listOf(
        Font(R.font.roboto_bold, FontWeight.W700),
        Font(R.font.roboto_medium, FontWeight.W500),
        Font(R.font.roboto_regular, FontWeight.W400)
    )
)

// Set of Material typography styles to start with
val Typography = Typography(

    h1 = TextStyle(
        fontFamily = Roboto,
        fontSize = 20.sp
    ),
    h2 = TextStyle(
        fontFamily = Roboto,
        fontSize = 15.sp
    ),
    body1 = TextStyle(
        fontFamily = Roboto,
        fontSize = 13.sp
    ),
    body2 = TextStyle(
        fontFamily = Roboto,
        fontSize = 12.sp
    )
)