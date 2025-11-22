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
        val random = Random.Default
        val randomColor = Color(
            red = random.nextInt(256),
            green = random.nextInt(256),
            blue = random.nextInt(256),
            alpha = 255
        )

        _stateFlow.update { state ->
            state.copy(boxColor = randomColor)
        }
    }
}