package com.example.jetpackcomposelesson
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.example.jetpackcomposelesson.ui.theme.JetpackComposeLessonTheme
import com.example.jetpackcomposelesson.ui.theme.LocalThemeManager
import com.example.jetpackcomposelesson.ui.theme.rememberThemeManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeManager = rememberThemeManager(isDarkTheme = false)

            CompositionLocalProvider(LocalThemeManager provides themeManager) {
                JetpackComposeLessonTheme(isDarkTheme = themeManager.isDarkTheme) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        MainApp(modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}