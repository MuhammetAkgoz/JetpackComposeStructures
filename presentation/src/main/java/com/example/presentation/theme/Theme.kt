package com.example.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.presentation.theme.color.DarkColorScheme
import com.example.presentation.theme.color.DarkColorTheme
import com.example.presentation.theme.color.LightColorScheme
import com.example.presentation.theme.color.LightColorTheme
import com.example.presentation.theme.color.LocalBaseColorTheme

@Composable
fun JetpackComposeLessonTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (isDarkTheme) DarkColorScheme else LightColorScheme

    val baseColorTheme = when {
        isDarkTheme -> DarkColorTheme
        else -> LightColorTheme
    }


    CompositionLocalProvider(
        LocalBaseColorTheme provides baseColorTheme,
    ) {
        MaterialTheme(
            colorScheme = colors,
            typography = Typography,
            content = content
        )
    }
}