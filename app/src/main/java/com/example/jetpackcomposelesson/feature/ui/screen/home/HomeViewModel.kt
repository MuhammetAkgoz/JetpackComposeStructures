package com.example.jetpackcomposelesson.feature.ui.screen.home

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


class HomeViewModel : ViewModel() {
    private val _stateFlow = MutableStateFlow(HomeState(Color.Black))
    val state: StateFlow<HomeState> = _stateFlow

    fun setTitle(title: String?) {
        _stateFlow.update { it.copy(title = title) }
    }
}