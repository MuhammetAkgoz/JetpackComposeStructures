package com.example.jetpackcomposelesson.feature.ui.screen.search

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SearchViewModel: ViewModel() {
    private val _state = MutableStateFlow(SearchState("search"))
    val state = _state.asStateFlow()
}