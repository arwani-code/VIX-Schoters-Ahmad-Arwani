package com.arwani.ahmad.schotersnews.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable


private val DarkColorPalette = darkColors(
    primary = Black200,
    primaryVariant = Black100,
    secondary = White,
    surface = Grey100,
)

private val LightColorPalette = lightColors(
    primary = Black200,
    primaryVariant = Black100,
    secondary = White,
    surface = Grey100,
)

@Composable
fun SchotersNewsTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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