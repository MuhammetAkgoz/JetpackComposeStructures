package com.example.jetpackcomposelesson.ui.screen.home

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random


class HomeViewModel : ViewModel() {
    private val _stateFlow = MutableStateFlow(HomeState(boxColor = Color.Black))
    val state: StateFlow<HomeState> = _stateFlow

    fun changeColor(){
        val random = Random.Default // Veya kotlin.random.Random
        val randomColor = Color(
            red = random.nextInt(256),   // 0-255 arası
            green = random.nextInt(256), // 0-255 arası
            blue = random.nextInt(256),  // 0-255 arası
            alpha = 255                  // Tamamen opak (görünür)
        )

        _stateFlow.update { state ->
            state.copy(boxColor = randomColor)
        }
    }
}