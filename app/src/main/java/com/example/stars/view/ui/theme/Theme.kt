package com.example.stars.view.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = DarkGrey,
    primaryVariant = DarkGrey800,
    secondary = LightGrey,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onSurface = Color.White
    /* background = White,
     surface = Color.White,
     onBackground = Color.Black,
     onSurface = Color.White*/

)

private val LightColorPalette = lightColors(
    primary = LightGrey,
    primaryVariant = LightGrey400,
    secondary = DarkGrey,
    onBackground = Color.Black,
    onPrimary = DarkGrey,
    onSecondary = Color.Black,
    onSurface = Color.Black,
    // Other default colors to override
    /* background = LightGrey,
     surface = LightGrey */
)

@Composable
fun StarsTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
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