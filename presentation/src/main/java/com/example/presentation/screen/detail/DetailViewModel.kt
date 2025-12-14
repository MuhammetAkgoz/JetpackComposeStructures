package com.example.presentation.screen.detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class DetailViewModel : ViewModel(){
    private val _state = MutableStateFlow(DetailState("detail"))
    val state = _state.asStateFlow()
}