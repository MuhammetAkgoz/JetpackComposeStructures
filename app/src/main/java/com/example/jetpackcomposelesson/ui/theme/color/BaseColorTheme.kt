package com.example.jetpackcomposelesson.ui.theme.color
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class BaseColorTheme (
    val primaryColor: Color = Color.Unspecified,
    val secondaryColor: Color = Color.Unspecified,
    val textColor: Color= Color.Unspecified,
    val iconColor: Color= Color.Unspecified,
)

/**
 * Uygulamanın her yerine, parametre olarak tek tek taşımadan (ellden ele vermeden),
 * havadan veri göndermemi sağlayan bir tünel.
 */
val LocalBaseColorTheme = staticCompositionLocalOf { BaseColorTheme() }