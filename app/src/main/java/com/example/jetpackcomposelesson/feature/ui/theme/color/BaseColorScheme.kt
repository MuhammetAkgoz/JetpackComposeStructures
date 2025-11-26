package com.example.jetpackcomposelesson.feature.ui.theme.color

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color


val LightColorScheme = lightColorScheme(
    background = Color.White,
)

val DarkColorScheme = darkColorScheme(
    background = Color.Black.copy(alpha = 0.8F)
)


val MaterialTheme.baseColorTheme: BaseColorTheme
    @Composable
    @ReadOnlyComposable
    get() = LocalBaseColorTheme.current