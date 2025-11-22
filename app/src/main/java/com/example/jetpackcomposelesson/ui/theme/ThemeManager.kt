package com.example.jetpackcomposelesson.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf

data class ThemeManager(
    val isDarkTheme : Boolean = false,
    val changeTheme: () -> Unit = {}
)

@Composable
fun rememberThemeManager(
    isDarkTheme: Boolean = isSystemInDarkTheme()
): ThemeManager {
    var isDark by rememberSaveable { mutableStateOf(isDarkTheme) }

    return remember(isDark) {
        ThemeManager(
            isDarkTheme = isDark,
            changeTheme = { isDark = !isDark }
        )
    }
}

val LocalThemeManager = staticCompositionLocalOf<ThemeManager> {
    error("Theme manager is not provided")
}


