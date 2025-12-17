package com.example.presentation.screen.home

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.functional.cross
import com.example.domain.repository.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: RickAndMortyRepository
) : ViewModel() {
    private val _stateFlow = MutableStateFlow(HomeState(Color.Black))
    val state: StateFlow<HomeState> = _stateFlow


    fun getCharacters() {
        viewModelScope.launch {
            repository.getCharacters().cross(
                right = { characters ->
                    characters.map {
                       it
                    }
                },
                left = {
                    print(it)
                }
            )
        }
    }
}